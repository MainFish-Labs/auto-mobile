package com.mainfish.europrotocola04;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by artli on 05.03.2016.
 */
public class EPR_p05_Circumstances extends AppCompatActivity {

	public int total = 18;
	public Cursor t4A_cursor, t4B_cursor;

    public int[] countA = new int[total], countB = new int[total];
    public String[] answersA = new String[total], answersB = new String[total];

    public TextView CountA;
    public TextView CountB;
	public String CountResultA = "0", CountResultB = "0";

	public String yes = "Да", no = "Нет", empty = "";
    public String textTSA = "\nВодитель ТС A - ", textTSB = "\nВодитель ТС B - ";

    private String[] infoText, q = new String[total];
    private ListView mDrawerListView;

    public boolean mSlideState;
    private DrawerLayout mDrawerLayout;

	public SwitchCompat[] switchA, switchB;

	private EPR_system_DataBaseContainer mDataBase;
	private SQLiteDatabase dbCircum;
	private String db_circum, db_circumA_name = "t4_circumA", db_circumB_name = "t4_circumB";
	private TextView previewCircum, closePreview;
	private ScrollView previewScroll;
	private ImageView previewImg;
	private TableLayout previewTable;
	private TableRow previewRow1, previewRow2;

	public String[] valuesCircumA_temp = new String[total], valuesCircumB_temp = new String[total];

	public File Path_sys = android.os.Environment.getExternalStorageDirectory();
	public File dataCheckFile, dataCheckAll;
	public String dataCheckNameCircum = "/db_circum.exist";
	public String dataCheckNameAll = "/db.exist";
	public String dataCheckPath = "/am_cache";
	public String Path = Path_sys + dataCheckPath;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.epr_p05_circumstances);

	    mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);
	    dbCircum = mDataBase.getWritableDatabase();

	    dataCheckAll = new File(Path, dataCheckNameAll);
	    dataCheckFile = new File(Path, dataCheckNameCircum);

	    setFields();
	    setUpDrawers();
	    handleSwitchesCircumstances();

	    if (dataCheckFile.exists() && dataCheckAll.exists()) {
		    getCursor();
	    }

    }

	/** Инициализация полей ввода */

	public void setFields () {

		switchA = new SwitchCompat[]{
				(SwitchCompat) findViewById(R.id.circum_a_01),
				(SwitchCompat) findViewById(R.id.circum_a_02),
				(SwitchCompat) findViewById(R.id.circum_a_03),
				(SwitchCompat) findViewById(R.id.circum_a_04),
				(SwitchCompat) findViewById(R.id.circum_a_05),
				(SwitchCompat) findViewById(R.id.circum_a_06),
				(SwitchCompat) findViewById(R.id.circum_a_07),
				(SwitchCompat) findViewById(R.id.circum_a_08),
				(SwitchCompat) findViewById(R.id.circum_a_09),
				(SwitchCompat) findViewById(R.id.circum_a_10),
				(SwitchCompat) findViewById(R.id.circum_a_11),
				(SwitchCompat) findViewById(R.id.circum_a_12),
				(SwitchCompat) findViewById(R.id.circum_a_13),
				(SwitchCompat) findViewById(R.id.circum_a_15),
				(SwitchCompat) findViewById(R.id.circum_a_14),
				(SwitchCompat) findViewById(R.id.circum_a_16),
				(SwitchCompat) findViewById(R.id.circum_a_17),
				(SwitchCompat) findViewById(R.id.circum_a_18)
		};
		switchB = new SwitchCompat[]{
				(SwitchCompat) findViewById(R.id.circum_b_01),
				(SwitchCompat) findViewById(R.id.circum_b_02),
				(SwitchCompat) findViewById(R.id.circum_b_03),
				(SwitchCompat) findViewById(R.id.circum_b_04),
				(SwitchCompat) findViewById(R.id.circum_b_05),
				(SwitchCompat) findViewById(R.id.circum_b_06),
				(SwitchCompat) findViewById(R.id.circum_b_07),
				(SwitchCompat) findViewById(R.id.circum_b_08),
				(SwitchCompat) findViewById(R.id.circum_b_09),
				(SwitchCompat) findViewById(R.id.circum_b_10),
				(SwitchCompat) findViewById(R.id.circum_b_11),
				(SwitchCompat) findViewById(R.id.circum_b_12),
				(SwitchCompat) findViewById(R.id.circum_b_13),
				(SwitchCompat) findViewById(R.id.circum_b_14),
				(SwitchCompat) findViewById(R.id.circum_b_15),
				(SwitchCompat) findViewById(R.id.circum_b_16),
				(SwitchCompat) findViewById(R.id.circum_b_17),
				(SwitchCompat) findViewById(R.id.circum_b_18),
		};

		CountA = (TextView) findViewById(R.id.total_a);
		CountB = (TextView) findViewById(R.id.total_b);
	}

	/** Обработка свитчей */

	public void handleSwitchesCircumstances () {

		if (!dataCheckFile.exists()) {
			for (int i=0; i < q.length; i++) {
				answersA[i] = no;
				answersB[i] = no;
			}
		}

		q = new String[]{
				"Не соблюдал безопасную дистанцию:",
				"Не соблюдал необходимый боковой интервал:",
				"Перестраивался в другую полосу:",
				"Поворачивал направо:",
				"Поворачивал налево:",
				"Разворачивался:",
				"Двигался задним ходом:",
				"Съезжал с проезжей части дороги:",
				"Выехал на перекресток на запрещающий сигнал светофора:",
				"Выехал на полосу встречного движения:",
				"Нарушил правила обгона:",
				"Начинал движение после остановки, стоянки:",
				"Не выполнил требования знака приоритета:",
				"Выезжал со второстепенной дороги, прилегающей территории:",
				"Двигался по прилегающей территории при наличии препятствия справа:",
				"Двигался по перекрестку с круговым движением:",
				"Совершил наезд на стоящее ТС:",
				"Иное нарушение, неуказанное в подпунктах 1–17:"
		};


		for (int i=0; i < q.length; i++) {

			final int finalI = i;
			switchA[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Toast.makeText(EPR_p05_Circumstances.this, q[finalI] + textTSA + (isChecked ? yes : no), Toast.LENGTH_SHORT).show();
					if (isChecked) {
						countA[finalI] = 1;
						answersA[finalI] = yes;
					} else {
						countA[finalI] = 0;
						answersA[finalI] = no;
					}
					countResultA();
				}
			});

			final int finalI1 = i;
			switchB[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Toast.makeText(EPR_p05_Circumstances.this, q[finalI1] + textTSB + (isChecked ? yes : no), Toast.LENGTH_SHORT).show();
					if (isChecked) {
						countB[finalI1] = 1;
						answersB[finalI] = yes;
					} else {
						countB[finalI1] = 0;
						answersB[finalI] = no;
					}
					countResultB();
				}
			});

		}

	}

	public void countResultA() {

		int Result=0;
		for(int i=0; i<countA.length; i++) {
			Result=Result+countA[i];
		}

		CountResultA = "" + Result;

		CountA.setText(CountResultA);
	}

	public void countResultB() {

		int Result=0;
		for(int i=0; i<countB.length; i++) {
			Result=Result+countB[i];
		}

		CountResultB = "" + Result;

		CountB.setText(CountResultB);
	}
	
	
	
	/** Вставка сохранённых значений **/
	
	private String[] inputGenA_temp, inputGenB_temp;
	
	public void getCursor () {
		
		inputGenA_temp = new String[total];
		inputGenB_temp = new String[total];
		
		t4A_cursor = dbCircum.query(db_circumA_name, new String[]{
						EPR_system_DataBaseContainer.T4A_01,
						EPR_system_DataBaseContainer.T4A_02,
						EPR_system_DataBaseContainer.T4A_03,
						EPR_system_DataBaseContainer.T4A_04,
						EPR_system_DataBaseContainer.T4A_05,
						EPR_system_DataBaseContainer.T4A_06,
						EPR_system_DataBaseContainer.T4A_07,
						EPR_system_DataBaseContainer.T4A_08,
						EPR_system_DataBaseContainer.T4A_09,
						EPR_system_DataBaseContainer.T4A_10,
						EPR_system_DataBaseContainer.T4A_11,
						EPR_system_DataBaseContainer.T4A_12,
						EPR_system_DataBaseContainer.T4A_13,
						EPR_system_DataBaseContainer.T4A_14,
						EPR_system_DataBaseContainer.T4A_15,
						EPR_system_DataBaseContainer.T4A_16,
						EPR_system_DataBaseContainer.T4A_17,
						EPR_system_DataBaseContainer.T4A_18
				},
				null, null, null, null, null);
		
		t4A_cursor.moveToFirst();
		if (t4A_cursor != null && t4A_cursor.moveToFirst()) {
			
			inputGenA_temp = new String[] {
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_01)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_02)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_03)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_04)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_05)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_06)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_07)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_08)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_09)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_10)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_11)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_12)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_13)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_14)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_15)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_16)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_17)),
					t4A_cursor.getString(t4A_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4A_18))
			};

			t4A_cursor.close();
			t4A_cursor.moveToFirst();
			
		}
		
		t4B_cursor = dbCircum.query(db_circumB_name, new String[]{
						EPR_system_DataBaseContainer.T4B_01,
						EPR_system_DataBaseContainer.T4B_02,
						EPR_system_DataBaseContainer.T4B_03,
						EPR_system_DataBaseContainer.T4B_04,
						EPR_system_DataBaseContainer.T4B_05,
						EPR_system_DataBaseContainer.T4B_06,
						EPR_system_DataBaseContainer.T4B_07,
						EPR_system_DataBaseContainer.T4B_08,
						EPR_system_DataBaseContainer.T4B_09,
						EPR_system_DataBaseContainer.T4B_10,
						EPR_system_DataBaseContainer.T4B_11,
						EPR_system_DataBaseContainer.T4B_12,
						EPR_system_DataBaseContainer.T4B_13,
						EPR_system_DataBaseContainer.T4B_14,
						EPR_system_DataBaseContainer.T4B_15,
						EPR_system_DataBaseContainer.T4B_16,
						EPR_system_DataBaseContainer.T4B_17,
						EPR_system_DataBaseContainer.T4B_18
				},
				null, null, null, null, null);
		
		t4B_cursor.moveToFirst();
		if (t4B_cursor != null && t4B_cursor.moveToFirst()) {
			
			inputGenA_temp = new String[] {
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_01)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_02)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_03)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_04)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_05)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_06)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_07)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_08)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_09)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_10)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_11)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_12)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_13)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_14)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_15)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_16)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_17)),
					t4B_cursor.getString(t4B_cursor.getColumnIndex(EPR_system_DataBaseContainer.T4B_18))
			};

			t4B_cursor.close();
			t4B_cursor.moveToFirst();
			
		}
		
		boolean[] checkBoolA = new boolean[total];
		boolean[] checkBoolB = new boolean[total];

		for (int i=0; i < total; i++) {
			checkBoolA[i] = (inputGenA_temp[i] == "Да");
			checkBoolB[i] = (inputGenB_temp[i] == "Да");

			if (checkBoolA[i]) {
				switchA[i].setChecked(checkBoolA[i]);
			}

			if (checkBoolB[i]) {
				switchB[i].setChecked(checkBoolB[i]);
			}
		}
		
	}
	
	/** Конец Вставка сохранённых значений **/



	/** Обработка базы данных */

	public void getBase () {

		ContentValues t4a_values = new ContentValues();
		ContentValues t4b_values = new ContentValues();

		// Значения столбцов
		t4a_values.put(EPR_system_DataBaseContainer.T4A_01, answersA[0]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_02, answersA[1]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_03, answersA[2]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_04, answersA[3]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_05, answersA[4]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_06, answersA[5]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_07, answersA[6]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_08, answersA[7]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_09, answersA[8]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_10, answersA[9]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_11, answersA[10]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_12, answersA[11]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_13, answersA[12]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_14, answersA[13]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_15, answersA[14]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_16, answersA[15]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_17, answersA[16]);
		t4a_values.put(EPR_system_DataBaseContainer.T4A_18, answersA[17]);

		// Значения столбцов
		t4b_values.put(EPR_system_DataBaseContainer.T4B_01, answersB[0]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_02, answersB[1]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_03, answersB[2]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_04, answersB[3]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_05, answersB[4]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_06, answersB[5]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_07, answersB[6]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_08, answersB[7]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_09, answersB[8]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_10, answersB[9]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_11, answersB[10]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_12, answersB[11]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_13, answersB[12]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_14, answersB[13]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_15, answersB[14]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_16, answersB[15]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_17, answersB[16]);
		t4b_values.put(EPR_system_DataBaseContainer.T4B_18, answersB[17]);

		// Вставляем данные в таблицу

		if (dataCheckFile.exists() && dataCheckAll.exists()) {
			dbCircum.update(
					db_circumA_name,
					t4a_values,
							EPR_system_DataBaseContainer.T4A_01  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_02  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_03  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_04  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_05  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_06  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_07  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_08  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_09  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_10  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_11  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_12  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_13  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_14  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_15  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_16  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_17  + "= ? OR " +
							EPR_system_DataBaseContainer.T4A_18 + "= ?",
					new String[]{
							answersA[0],
							answersA[1],
							answersA[2],
							answersA[3],
							answersA[4],
							answersA[5],
							answersA[6],
							answersA[7],
							answersA[8],
							answersA[9],
							answersA[10],
							answersA[11],
							answersA[12],
							answersA[13],
							answersA[14],
							answersA[15],
							answersA[16],
							answersA[17]
					}
			);
		} else {
			dbCircum.insert(
					db_circumA_name,
					null,
					t4a_values
			);
			checkFileCreate();
		}

		if (dataCheckFile.exists() && dataCheckAll.exists()) {
			dbCircum.update(
					db_circumB_name,
					t4b_values,
							EPR_system_DataBaseContainer.T4B_01  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_02  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_03  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_04  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_05  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_06  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_07  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_08  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_09  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_10  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_11  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_12  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_13  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_14  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_15  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_16  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_17  + "= ? OR " +
							EPR_system_DataBaseContainer.T4B_18 + "= ?",
					new String[]{
							answersB[0],
							answersB[1],
							answersB[2],
							answersB[3],
							answersB[4],
							answersB[5],
							answersB[6],
							answersB[7],
							answersB[8],
							answersB[9],
							answersB[10],
							answersB[11],
							answersB[12],
							answersB[13],
							answersB[14],
							answersB[15],
							answersB[16],
							answersB[17]
					}
			);
		} else {
			dbCircum.insert(
					db_circumB_name,
					null,
					t4b_values
			);
			checkFileCreate();
		}
		
		assert previewCircum != null;

		String shortPreview = "";
		String[] forPreview = new String[total];

		for (int i=0; i < total; i++) {
			if (answersA[i] == null) {
				answersA[i] = no;
			}
			if (answersB[i] == null) {
				answersB[i] = no;
			}
			forPreview[i] = "\n" +q[i] + textTSA + answersA[i] +
					textTSB + answersB[i];
			if (answersA[i] == yes || answersB[i] == yes) {
				shortPreview = shortPreview + "\n" + forPreview[i];
			}
		}

		db_circum = shortPreview +
			"\n\nКоличество отмеченных пунктов:" +
			textTSA + CountResultA +
			textTSB + CountResultB;

	}

	public void checkFileCreate () {

		String sdState = android.os.Environment.getExternalStorageState();
		if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			dataCheckFile = new File(Path_sys, dataCheckPath + dataCheckNameCircum);
			dataCheckAll = new File(Path_sys, dataCheckPath + dataCheckNameAll);
		} else {
			dataCheckFile = getApplicationContext().getCacheDir();
			dataCheckAll = getApplicationContext().getCacheDir();
		}

		if (!dataCheckFile.exists()) {
			try {
				dataCheckFile.createNewFile();

				try {
					FileWriter fWr = new FileWriter(dataCheckFile);
					fWr.write("database exist");
					fWr.flush();
					fWr.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch (Exception e1) {
				return;
			}
		}

		if (!dataCheckAll.exists()) {
			try {
				dataCheckAll.createNewFile();

				try {
					FileWriter fWr = new FileWriter(dataCheckAll);
					fWr.write("database exist");
					fWr.flush();
					fWr.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch (Exception e1) {
				return;
			}
		}

	}


	/** Конец Обработка базы данных **/



	/** System */

	public void setUpDrawers () {

	    infoText = getResources().getStringArray(R.array.info_button4);
	    mDrawerListView = (ListView) findViewById(R.id.info_drawer);

	    // подключим адаптер для списка
	    mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
			    R.layout.epr_system_info_drawer, infoText));

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_circum);
		mSlideState = false;

		mDrawerLayout.setDrawerListener(new ActionBarDrawerToggle(this,
				mDrawerLayout,
				0,
				0){
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				mSlideState=false;//is Closed
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				mSlideState=true;//is Opened
			}});
    }

    public void gotoSchemePage (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    getBase();

        Intent intentSchemePage = new Intent(this, EPR_p06_SchemePage.class);
        startActivity(intentSchemePage);

    }

    public void gotoDriverB (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    getBase();

        Intent intentDriverB = new Intent(this, EPR_p04_DriverB.class);
        startActivity(intentDriverB);

    }

    public void btnOpenI (View view) {

        if(mSlideState){
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.END);
        }

    }

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
			mDrawerLayout.closeDrawer(GravityCompat.END);
		} else {

			ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");

			getBase();
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){

			case R.id.action_preview:

				getBase();
				previewCircum = (TextView)findViewById(R.id.testBase);
				previewCircum.setText(db_circum, TextView.BufferType.EDITABLE);
				previewCircum.setVisibility((previewCircum.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				closePreview = (TextView)findViewById(R.id.preview_close);
				closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewScroll = (ScrollView)findViewById(R.id.testBaseScroll);
				previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewTable = (TableLayout)findViewById(R.id.testBaseTable);
				previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewRow1 = (TableRow)findViewById(R.id.testBaseRow1);
				previewRow1.setVisibility((previewRow1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				Toast.makeText(getApplicationContext(),
						"Предварительный просмотр", Toast.LENGTH_SHORT).show();
				break;

			case R.id.action_clear_db:

				ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");

				File dbSelf = new File("/data/data/com.mainfish.europrotocola04/databases/am_protocol.db");
				dbSelf.delete();

				dataCheckFile.delete();
				dataCheckAll.delete();

				File oldBaseDrA = new File(Path, "/db_drA.exist");
				oldBaseDrA.delete();

				File oldBaseDrB = new File(Path, "/db_drB.exist");
				oldBaseDrB.delete();

				Intent intentAccident = new Intent(this, EPR_p01_Accident.class);
				startActivity(intentAccident);
				Toast.makeText(getApplicationContext(),
						"База данных очищена", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void closePreview (View view) {

		previewCircum.setVisibility((previewCircum.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewRow1.setVisibility((previewRow1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

	}

	/** -- **/



}