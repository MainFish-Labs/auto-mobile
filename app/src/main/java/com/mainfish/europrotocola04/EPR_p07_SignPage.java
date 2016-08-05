package com.mainfish.europrotocola04;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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
 * Created by artli on 12.05.2016.
 */
public class EPR_p07_SignPage extends AppCompatActivity {

    private String[] infoText;
    private ListView mDrawerListView;

    public boolean mSlideState;
    private DrawerLayout mDrawerLayout;

	/** Переменные полей ввода */

	public ImageView sign_SignA, sign_SignB, sign_SignA_resp, sign_SignB_resp;

	public EditText sign_PrimA, sign_PrimB;

	public TextView sign_FIOA, sign_FIOB, sign_FIOA_resp, sign_FIOB_resp;

	/** Переменные базы данных */

	public Cursor t6a_cursor, t6b_cursor; // !!!!

	public String yes = "Да", no = "Нет", empty = "";

	private EPR_system_DataBaseContainer mDataBase;
	private SQLiteDatabase dbSign;
	private String db_signA, db_signB, db_signA_name = "t6a_sign", db_signB_name = "t6b_sign";
	private TextView previewSign, closePreview;
	private ScrollView previewScroll;
	private ImageView previewImg;
	private TableLayout previewTable;
	private TableRow previewRow1, previewRow2;

	public int total = 5;
	
	public String[] valuesSign_temp = new String[total];

	public String primA, primB, nameA, nameB, nameA_resp, nameB_resp, signA, signB, signA_resp, signB_resp;

	public File Path_sys = android.os.Environment.getExternalStorageDirectory();
	public File dataCheckFile,dataCheckAll;
	public String dataCheckNameSign = "/db_sign.exist";
	public String dataCheckNameAll = "/db.exist";
	public String dataCheckPath = "/am_cache";
	public String Path = Path_sys + dataCheckPath;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.epr_p07_sign_layout);

	    mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);
	    dbSign = mDataBase.getWritableDatabase();

	    dataCheckAll = new File(Path, dataCheckNameAll);
	    dataCheckFile = new File(Path, dataCheckNameSign);

        setUpDrawers();
	    setFields();
	    handleSigns();
	
	    if (dataCheckFile.exists() && dataCheckAll.exists()) {
		    getCursor();
	    }

    }

	/** Инициализация полей ввода */

	public void setFields () {

		sign_SignA = (ImageView) findViewById(R.id.img_signA);
		sign_SignB = (ImageView) findViewById(R.id.img_signB);
		sign_SignA_resp = (ImageView) findViewById(R.id.img_signA_resp);
		sign_SignB_resp = (ImageView) findViewById(R.id.img_signB_resp);

		sign_PrimA = (EditText) findViewById(R.id.primA);
		sign_PrimB = (EditText) findViewById(R.id.primB);

		sign_FIOA = (TextView) findViewById(R.id.signA_fio);
		sign_FIOB = (TextView) findViewById(R.id.signB_fio);
		sign_FIOA_resp = (TextView) findViewById(R.id.signA_fio_resp);
		sign_FIOB_resp = (TextView) findViewById(R.id.signB_fio_resp);

	}

	public void handleSigns () {

		if (!dataCheckFile.exists()) {
			sign_SignA.setImageResource(R.drawable.logo);
			sign_SignB.setImageResource(R.drawable.logo);
			sign_SignA_resp.setImageResource(R.drawable.logo);
			sign_SignB_resp.setImageResource(R.drawable.logo);
		}
		sign_SignA.setImageResource(R.drawable.logo);
		sign_SignB.setImageResource(R.drawable.logo);
		sign_SignA_resp.setImageResource(R.drawable.logo);
		sign_SignB_resp.setImageResource(R.drawable.logo); /** После добавления модуля рисования - исправить */

	}
	
	
	
	/** Вставка сохранённых значений **/
	
	private String[] inputSignA_temp, inputSignB_temp;
	
	public void getCursor () {
		
		inputSignA_temp = new String[total];
		inputSignB_temp = new String[total];
		
		String[] t6a = new String[]{
				EPR_system_DataBaseContainer.T6A_PRIM,
				EPR_system_DataBaseContainer.T6A_SIGN,
				EPR_system_DataBaseContainer.T6A_NAME,
				EPR_system_DataBaseContainer.T6A_SIGN_RESP,
				EPR_system_DataBaseContainer.T6A_NAME_RESP
			
		};
		
		t6a_cursor = dbSign.query(db_signA_name, t6a,
				null, null, null, null, null);
		
		t6a_cursor.moveToFirst();
		if (t6a_cursor != null && t6a_cursor.moveToFirst()) {
			
			inputSignA_temp = new String[] {
					t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_PRIM)),
					t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_SIGN)),
					t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_NAME)),
					t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_SIGN_RESP)),
					t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_NAME_RESP))
			};
			
		}
		
		t6a_cursor.close();
		t6a_cursor.moveToFirst();
		
		sign_PrimA.setText(inputSignA_temp[0], TextView.BufferType.EDITABLE);
		sign_SignA.setImageDrawable(Drawable.createFromPath(inputSignA_temp[1]));
		sign_FIOA.setText(inputSignA_temp[2], TextView.BufferType.EDITABLE);
		sign_SignA_resp.setImageDrawable(Drawable.createFromPath(inputSignA_temp[3]));
		sign_FIOA_resp.setText(inputSignA_temp[4], TextView.BufferType.EDITABLE);
		
		String[] t6b = new String[]{
				EPR_system_DataBaseContainer.T6B_PRIM,
				EPR_system_DataBaseContainer.T6B_SIGN,
				EPR_system_DataBaseContainer.T6B_NAME,
				EPR_system_DataBaseContainer.T6B_SIGN_RESP,
				EPR_system_DataBaseContainer.T6B_NAME_RESP
			
		};
		
		t6b_cursor = dbSign.query(db_signB_name, t6b,
				null, null, null, null, null);
		
		t6b_cursor.moveToFirst();
		if (t6b_cursor != null && t6b_cursor.moveToFirst()) {
			
			inputSignB_temp = new String[] {
					t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_PRIM)),
					t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_SIGN)),
					t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_NAME)),
					t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_SIGN_RESP)),
					t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_NAME_RESP))
			};
			
		}
		
		t6b_cursor.close();
		t6b_cursor.moveToFirst();
		
		sign_PrimB.setText(inputSignB_temp[0], TextView.BufferType.EDITABLE);
		sign_SignB.setImageDrawable(Drawable.createFromPath(inputSignB_temp[1]));
		sign_FIOB.setText(inputSignB_temp[2], TextView.BufferType.EDITABLE);
		sign_SignB_resp.setImageDrawable(Drawable.createFromPath(inputSignB_temp[3]));
		sign_FIOB_resp.setText(inputSignB_temp[4], TextView.BufferType.EDITABLE);

	}
	
	/** Конец Вставка сохранённых значений **/
	
	/** Обработка базы данных */
	
	public void getStrings () {
		
		valuesSign_temp = new String[]{
				sign_PrimA.getText().toString(),
				signA,
				sign_FIOA.getText().toString(),
				signA_resp,
				sign_FIOA_resp.getText().toString(),
				sign_PrimB.getText().toString(),
				signB,
				sign_FIOB.getText().toString(),
				signB_resp,
				sign_FIOB_resp.getText().toString()
		};
		
		for (int i=0; i < valuesSign_temp.length; i++) {
			if (valuesSign_temp[i] == null) {
				valuesSign_temp[i] = "";
			}
		}
		
		primA = valuesSign_temp[0];
		signA = valuesSign_temp[1];
		nameA = valuesSign_temp[2];
		signA_resp = valuesSign_temp[3];
		nameA_resp = valuesSign_temp[4];
		
		primB = valuesSign_temp[5];
		signB = valuesSign_temp[6];
		nameB = valuesSign_temp[7];
		signB_resp = valuesSign_temp[8];
		nameB_resp = valuesSign_temp[9];
		
	}
	
	public void getBase () {
		
		getStrings();
		
		ContentValues t6a_values = new ContentValues();
		ContentValues t6b_values = new ContentValues();
		
		// Значения столбцов
		t6a_values.put(EPR_system_DataBaseContainer.T6A_PRIM, primA);
		t6a_values.put(EPR_system_DataBaseContainer.T6A_SIGN, signA);
		t6a_values.put(EPR_system_DataBaseContainer.T6A_NAME, nameA);
		t6a_values.put(EPR_system_DataBaseContainer.T6A_SIGN_RESP, signA_resp);
		t6a_values.put(EPR_system_DataBaseContainer.T6A_NAME_RESP, nameA_resp);
		
		// Значения столбцов
		t6b_values.put(EPR_system_DataBaseContainer.T6B_PRIM, primB);
		t6b_values.put(EPR_system_DataBaseContainer.T6B_SIGN, signB);
		t6b_values.put(EPR_system_DataBaseContainer.T6B_NAME, nameB);
		t6b_values.put(EPR_system_DataBaseContainer.T6B_SIGN_RESP, signB_resp);
		t6b_values.put(EPR_system_DataBaseContainer.T6B_NAME_RESP, nameB_resp);
		
		// Вставляем данные в таблицу
		
		if (dataCheckFile.exists() && dataCheckAll.exists()) {
			dbSign.update(
					db_signA_name,
					t6a_values,
					EPR_system_DataBaseContainer.T6A_PRIM + "= ? OR " +
							EPR_system_DataBaseContainer.T6A_SIGN + "= ? OR " +
							EPR_system_DataBaseContainer.T6A_NAME + "= ? OR " +
							EPR_system_DataBaseContainer.T6A_SIGN_RESP + "= ? OR " +
							EPR_system_DataBaseContainer.T6A_NAME_RESP + "= ?",
					new String[]{
							primA,
							signA,
							nameA,
							signA_resp,
							nameA_resp
					}
			);
		} else {
			dbSign.insert(
					db_signA_name,
					null,
					t6a_values
			);
			checkFileCreate();
		}
		
		assert previewSign != null;
		db_signA =
				"Примечание водителя ТС A   " + primA
				+ "\n   " + signA
				+ "\nИмя  " + nameA
				+ "\nОтчество   " + signA_resp
				+ "\nНаим. юр. лица   " + nameA_resp
				+ "\n"
				+ "Примечание водителя ТС A   " + primB
				+ "\nФамилия   " + signB
				+ "\nИмя  " + nameB
				+ "\nОтчество   " + signB_resp
				+ "\nНаим. юр. лица   " + nameB_resp;
		
	}
	
	public void checkFileCreate () {
		
		String sdState = android.os.Environment.getExternalStorageState();
		if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			dataCheckFile = new File(Path_sys, dataCheckPath + dataCheckNameDrB);
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


	/** System */

    public void setUpDrawers () {
        infoText = getResources().getStringArray(R.array.info_button6);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.epr_system_info_drawer, infoText));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_sign);
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
	    getBase();

        Intent intentSchemePage = new Intent(this, EPR_p06_SchemePage.class);
        startActivity(intentSchemePage);

    }

	public void gotoReadyPage (View view) {
		getBase();

		Intent intentReadyPage = new Intent(this, EPR_p08_ReadyPage.class);
		startActivity(intentReadyPage);

	}

    public void btnOpenI (View view) {

        //mDrawerListView = (ListView) findViewById(R.id.info_drawer);
        //mDrawerListView.setVisibility(View.VISIBLE);

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
				previewDriverB = (TextView)findViewById(R.id.testBase);
				previewDriverB.setText(db_driverB, TextView.BufferType.EDITABLE);
				previewDriverB.setVisibility((previewDriverB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				closePreview = (TextView)findViewById(R.id.preview_close);
				closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewScroll = (ScrollView)findViewById(R.id.testBaseScroll);
				previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewImg = (ImageView)findViewById(R.id.testBaseImg);
				previewImg.setImageResource(R.drawable.logo); /** Временное явление */
				previewImg.setVisibility((previewImg.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewTable = (TableLayout)findViewById(R.id.testBaseTable);
				previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewRow1 = (TableRow)findViewById(R.id.testBaseRow1);
				previewRow1.setVisibility((previewRow1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				previewRow2 = (TableRow)findViewById(R.id.testBaseRow2);
				previewRow2.setVisibility((previewRow2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				Toast.makeText(getApplicationContext(),
						"Предварительный просмотр", Toast.LENGTH_SHORT).show();
				break;

			case R.id.action_clear_db:
				File dbSelf = new File("/data/data/com.mainfish.europrotocola04/databases/am_protocol.db");
				dbSelf.delete();

				dataCheckFile.delete();
				dataCheckAll.delete();

				File oldBaseDrGen = new File(Path, "/db_gen.exist");
				oldBaseDrGen.delete();

				File oldBaseDrA = new File(Path, "/db_drA.exist");
				oldBaseDrA.delete();

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

		previewDriverB.setVisibility((previewDriverB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewImg.setVisibility((previewImg.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewRow1.setVisibility((previewRow1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewRow2.setVisibility((previewRow2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

	}

}