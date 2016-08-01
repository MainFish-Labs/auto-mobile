package com.mainfish.europrotocola04;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by artli on 05.03.2016.
 */

public class General extends AppCompatActivity {

	/** Переменные полей ввода */

	public EditText setDate;
	public EditText setTime;

	public TextView setPlace;
	public EditText setGeo;
	public EditText setCity;

	public SwitchCompat setQ1;
	public SwitchCompat setQ2;
	public SwitchCompat setQ3;

	public EditText setWit1;
	public EditText setWit2;
	public EditText setWit3;
	public EditText setWit4;

	/** Переменные базы данных */

	public Cursor t1_cursor; // !!!!
	public boolean isDataBase;

	private DataBaseContainer mDataBaseContainer;
	private SQLiteDatabase mSQLiteDatabase;
	private String db_general;
	private TextView previewGeneral, closePreview;

	public String gen_date, gen_time, gen_country, gen_geo, gen_city;
	public String q_text1, q_text2, q_text3;
	public String wit_text1, wit_text2, wit_text3, wit_text4;

	public String[] valuesGen_temp = new String[12];

	public File dataCheckFile;
	public File sdDir = android.os.Environment.getExternalStorageDirectory();
	public String dataCheckPath = "/am_cache5/db.exist";

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

	    setContentView(R.layout.accident_general);

	    handleDataBaseGeneral();

	    checkDataBase();

	    if (!isDataBase) {

		    q_text1 = "Нет";
		    q_text2 = "Нет";
		    q_text3 = "Нет";

		    getDateTime();

	    } else {

		    getStored();

	    }

        setUpInfoDrawer();

        handleSwitchesGeneral();

    }



	/** Инициализация полей ввода **/

	public void setFields () {

		setDate = (EditText) findViewById(R.id.gen_input_date);
		setTime = (EditText) findViewById(R.id.gen_input_time);

		setPlace = (TextView) findViewById(R.id.gen_input_country);
		setGeo = (EditText) findViewById(R.id.gen_input_geo);
		setCity = (EditText) findViewById(R.id.gen_input_city);

		setQ1 = (SwitchCompat) findViewById(R.id.gen_switch_q1);
		setQ2 = (SwitchCompat) findViewById(R.id.gen_switch_q2);
		setQ3 = (SwitchCompat) findViewById(R.id.gen_switch_q3);

		setWit1 = (EditText) findViewById(R.id.wit1);
		setWit2 = (EditText) findViewById(R.id.wit2);
		setWit3 = (EditText) findViewById(R.id.wit3);
		setWit4 = (EditText) findViewById(R.id.wit4);

	}



    /** Info Drawer **/

    private String[] infoText;
    private ListView mDrawerListView;

    private DrawerLayout mDrawerLayout;

    public boolean mSlideState;

    public void setUpInfoDrawer () {

        infoText = getResources().getStringArray(R.array.info_button2);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.info_drawer_layout, infoText));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.accident_general);
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

    /** Конец Info Drawer **/



    /** Дата и время **/

    public boolean isAutoDateTime;

    public void getDateTime () {

        Calendar c = Calendar.getInstance();

        int[] dt = {
		        c.get(Calendar.DATE),
		        (c.get(Calendar.MONTH) + 1),
		        c.get(Calendar.YEAR),
		        c.get(Calendar.HOUR_OF_DAY),
		        c.get(Calendar.MINUTE),
        };

        String[] dt_values = new String[5];

        for (int i=0; i < dt.length; i++) {
	        if (dt[i] < 10) {
		        dt_values[i] = "0" + dt[i];
	        } else {
		        dt_values[i] = String.valueOf(dt[i]);
	        }
        }

        gen_date = dt_values[0] + "." + dt_values[1] + "." + dt_values[2];
        gen_time = dt_values[3] + ":" + dt_values[4];

	    isAutoDateTime = true;

	    setDate.setText(gen_date, TextView.BufferType.EDITABLE);
	    setTime.setText(gen_time,TextView.BufferType.EDITABLE);

    }

    /** Конец Дата и время **/



    /** Обработка свитчей */

    public int counter;

    public void handleSwitchesGeneral () {

	    final TextView btn_GoToA = (TextView) findViewById(R.id.goto_driverA);
	    final TextView btn_Impossible = (TextView) findViewById(R.id.btn_impossible);
	    btn_GoToA.setVisibility(View.VISIBLE);
	    btn_Impossible.setVisibility(View.GONE);

	    final int[] countQ = {
			    0,
			    0,
			    0
	    };

	    SwitchCompat[] controlQ = {
		        setQ1,
		        setQ2,
		        setQ3
        };

	    final String[] textQ = {
			    "Пострадавшие люди - ",
			    "Материальный вред прочим ТС - ",
			    "Материальный вред другому имуществу - "
	    };

	    final String[] answerQ = new String[3];

	    for (counter=0; counter < countQ.length; counter++) {

		    controlQ[counter].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			    int j = counter;
			    @Override
			    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				    Toast.makeText(General.this, (isChecked ? "В Вашем случае заполнение Извещения о ДТП невозможно\n" +
						    "(см. стр 1 «Обязательные условия»). Вызывайте ГАИ" : textQ[j] + "Нет"),Toast.LENGTH_SHORT).show();

				    if (isChecked) {
					    countQ[j]=1;
				    } else {
					    countQ[j]=0;
				    }

				    if ((countQ[0] == 1) || (countQ[1] == 1) || (countQ[2] == 1)) {

					    btn_GoToA.setVisibility(View.GONE);
					    btn_Impossible.setVisibility(View.VISIBLE);
					    answerQ[j] = "Да";

				    } else {

					    btn_Impossible.setVisibility(View.GONE);
					    btn_GoToA.setVisibility(View.VISIBLE);
					    answerQ[j] = "Нет";

				    }

			    }
		    });

		    q_text1 = answerQ[0];
		    q_text2 = answerQ[1];
		    q_text3 = answerQ[2];

	    }

    }

    /** Конец Обработка свитчей **/



    /** Обработка базы данных */

    public void handleDataBaseGeneral () {

        mDataBaseContainer = new DataBaseContainer(this, "am_protocol.db", null, 1);

        mSQLiteDatabase = mDataBaseContainer.getWritableDatabase();

	    setFields();

    }
	
	public void checkFileCreate () {
		
		String sdState = android.os.Environment.getExternalStorageState();
		if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			dataCheckFile = new File(sdDir, dataCheckPath);
		} else {
			dataCheckFile = getApplicationContext().getCacheDir();
		}

		if (!dataCheckFile.exists()) {
			dataCheckFile.mkdirs();
		}

		FileOutputStream fos;
		try {
			fos = openFileOutput("db.exist", Context.MODE_PRIVATE); // открываем файл для записи
			fos.write("database exist".getBytes()); // записываем данные
			fos.close(); // закрываем файл
			isDataBase = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void checkDataBase () {

		String Path = sdDir + dataCheckPath;

		File isExist = new File(Path, "/db.exist");

		if (isExist.exists()) {
			isDataBase = true;
		} else {
			isDataBase = false;
		}

	}

    public void getStrings () {

	    valuesGen_temp = new String[]{
			    setDate.getText().toString(),
			    setTime.getText().toString(),
			    setPlace.getText().toString(),
			    setGeo.getText().toString(),
			    setCity.getText().toString(),
			    q_text1,
			    q_text2,
			    q_text3,
			    setWit1.getText().toString(),
			    setWit2.getText().toString(),
			    setWit3.getText().toString(),
			    setWit4.getText().toString()
	    };

	    for (int i=0; i < valuesGen_temp.length; i++) {
		    if (valuesGen_temp[i] == null) {
			    valuesGen_temp[i] = "";
		    }
	    }

	    if (!isAutoDateTime) {
		    gen_date = valuesGen_temp[0];
		    gen_time = valuesGen_temp[1];
	    }

        gen_country = valuesGen_temp[2];
        gen_geo = valuesGen_temp[3];
        gen_city = valuesGen_temp[4];

	    q_text1 = valuesGen_temp[5];
	    q_text2 = valuesGen_temp[6];
	    q_text3 = valuesGen_temp[7];

        wit_text1 = valuesGen_temp[8];
        wit_text2 = valuesGen_temp[9];
        wit_text3 = valuesGen_temp[10];
        wit_text4 = valuesGen_temp[11];

    }

    public void getBase () {

	    getValues();

	    getPreview();

    }

	public void getValues () {

		getStrings();

        ContentValues t1_values = new ContentValues();

        // Значения столбцов
        t1_values.put(DataBaseContainer.T1_DATE, gen_date);
        t1_values.put(DataBaseContainer.T1_TIME, gen_time);
        t1_values.put(DataBaseContainer.T1_COUNTRY, gen_country);
        t1_values.put(DataBaseContainer.T1_GEO, gen_geo);
        t1_values.put(DataBaseContainer.T1_M_PLACE, gen_city);

        t1_values.put(DataBaseContainer.T1_Q1, q_text1);
        t1_values.put(DataBaseContainer.T1_Q2, q_text2);
        t1_values.put(DataBaseContainer.T1_Q3, q_text3);

        t1_values.put(DataBaseContainer.T1_W1, wit_text1);
        t1_values.put(DataBaseContainer.T1_W2, wit_text2);
        t1_values.put(DataBaseContainer.T1_W3, wit_text3);
        t1_values.put(DataBaseContainer.T1_W4, wit_text4);

        // Вставляем данные в таблицу

        if (!isDataBase) {

	        mSQLiteDatabase.insert(
			        "am_protocol",
			        null,
			        t1_values
	        );
	        checkFileCreate();

        } else {

	        mSQLiteDatabase.update(
			        "am_protocol",
			        t1_values,
			        mDataBaseContainer.T1_DATE + "= ? OR " +
					        mDataBaseContainer.T1_TIME + "= ? OR " +
					        mDataBaseContainer.T1_COUNTRY + "= ? OR " +
					        mDataBaseContainer.T1_GEO + "= ? OR " +
					        mDataBaseContainer.T1_M_PLACE + "= ? OR " +
					        mDataBaseContainer.T1_Q1 + "= ? OR " +
					        mDataBaseContainer.T1_Q2 + "= ? OR " +
					        mDataBaseContainer.T1_Q3 + "= ? OR " +
					        mDataBaseContainer.T1_W1 + "= ? OR " +
					        mDataBaseContainer.T1_W2 + "= ? OR " +
					        mDataBaseContainer.T1_W3 + "= ? OR " +
					        mDataBaseContainer.T1_W4 + "= ?",
			        new String[]{
					        gen_date,
					        gen_time,
					        gen_country,
					        gen_geo,
					        gen_city,
					        q_text1,
					        q_text2,
					        q_text3,
					        wit_text1,
					        wit_text2,
					        wit_text3,
					        wit_text4
			        }
	        );

        }

    }

	public void getPreview () {

		assert previewGeneral != null;
		db_general = "Дата ДТП:    " + gen_date + "\nВремя ДТП:    " + gen_time + "\nСтрана ДТП:    " + gen_country + "\nМесто ДПТ:    " + gen_geo + "\nМесто ДТП:    " + gen_city + "\nПострадавшие?    " + q_text1 + "\nВред транспорту?    " + q_text2 + "\nВред имуществу?    " + q_text3 + "\nСвидетель 1:    " + wit_text1 + "\nСвидетель 2:    " + wit_text2 + "\nСвидетель 3:    " + wit_text3 + "\nСвидетель 4:   " + wit_text4;

	}


	/** Конец Обработка базы данных **/



	/** Вставка сохранённых значений **/

	public void getStored () {

		t1_cursor = mSQLiteDatabase.query("am_protocol", new String[] {
						DataBaseContainer.T1_DATE,
						DataBaseContainer.T1_TIME,
						DataBaseContainer.T1_COUNTRY,
						DataBaseContainer.T1_GEO,
						DataBaseContainer.T1_M_PLACE,
						DataBaseContainer.T1_Q1,
						DataBaseContainer.T1_Q2,
						DataBaseContainer.T1_Q3,
						DataBaseContainer.T1_W1,
						DataBaseContainer.T1_W2,
						DataBaseContainer.T1_W3,
						DataBaseContainer.T1_W4
				},
				null, null, null, null, null);

		t1_cursor.moveToFirst();

		String[] inputGen_temp = {
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_DATE)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_TIME)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_COUNTRY)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_GEO)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_M_PLACE)),

				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_Q1)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_Q2)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_Q3)),

				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_W1)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_W2)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_W3)),
				t1_cursor.getString(t1_cursor.getColumnIndex(DataBaseContainer.T1_W4))
		};

		t1_cursor.close();
		t1_cursor.moveToFirst();

		setDate.setText(inputGen_temp[0], TextView.BufferType.EDITABLE);
		setTime.setText(inputGen_temp[1],TextView.BufferType.EDITABLE);
		setPlace.setText(inputGen_temp[2], TextView.BufferType.EDITABLE);
		setGeo.setText(inputGen_temp[3], TextView.BufferType.EDITABLE);
		setCity.setText(inputGen_temp[4], TextView.BufferType.EDITABLE);

		q_text1 = inputGen_temp[5];
		q_text2 = inputGen_temp[6];
		q_text3 = inputGen_temp[7];

		SwitchCompat[] setQ_temp = new SwitchCompat[3];

		boolean[] checkBool = new boolean[3];

		for (int i=0; i < setQ_temp.length; i++) {
			if (inputGen_temp[(5+i)] == "Да") {
				checkBool[i] = true;
			} else {
				checkBool[i] = false;
			}
			setQ_temp[i].setChecked(checkBool[i]);
		}


		setQ1 = setQ_temp[0];
		setQ2 = setQ_temp[1];
		setQ3 = setQ_temp[2];

		setWit1.setText(inputGen_temp[8], TextView.BufferType.EDITABLE);
		setWit2.setText(inputGen_temp[9], TextView.BufferType.EDITABLE);
		setWit3.setText(inputGen_temp[10], TextView.BufferType.EDITABLE);
		setWit4.setText(inputGen_temp[11], TextView.BufferType.EDITABLE);

	}

	/** Конец Вставка сохранённых значений **/



    /** Кнопки **/

    public void gotoMap (View view) {

	    getBase();

//        Intent intentMap = new Intent(this, SetLocation.class);
//        startActivity(intentMap);

    }

    public void gotoDriverA (View view) {

	    getBase();

        Intent intentDriverA = new Intent(this, DriverA.class);
        startActivity(intentDriverA);

    }

    public void gotoErrorPage (View view) {

	    getBase();

        Intent intentErrorPage = new Intent(this, PageError.class);
        startActivity(intentErrorPage);

    }

    public void accidentClick (View view) {

	    getBase();

        Intent intentAccident = new Intent(this, Accident.class);
        startActivity(intentAccident);

    }

    public void btnOpenI (View view) {

        if(mSlideState){
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.END);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.menu_main_menu, menu);
	    return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){

			case R.id.action_settings:

				getBase();
				previewGeneral = (TextView) findViewById(R.id.testBase);
				previewGeneral.setText(db_general, TextView.BufferType.EDITABLE);
				previewGeneral.setVisibility((previewGeneral.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				closePreview = (TextView) findViewById(R.id.preview_close);
				closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
				Toast.makeText(getApplicationContext(),
						"Предварительный просмотр", Toast.LENGTH_SHORT).show();

			case R.id.action_clear_db:
				File dbCheck = new File(dataCheckPath, "/db.exist");
				File dbSelf = new File("/data/data/com.mainfish.europrotocola04/databases/am_protocol.db");
				boolean delCheck = dbCheck.delete();
				boolean delSelf = dbSelf.delete();
				isDataBase = false;
				Toast.makeText(getApplicationContext(),
						"База данных очищена", Toast.LENGTH_SHORT).show();

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void closePreview (View view) {

		previewGeneral.setVisibility((previewGeneral.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

	}

	/** Конец Кнопки **/



	/** Отключено **/

//    public void showGeneral (View view) {
//
//
//        Intent intentGeneralShow = new Intent(this, GeneralShow.class);
//
//        intentGeneralShow.putExtra("getDate", setDate.getText().toString());
//        intentGeneralShow.putExtra("getTime", setTime.getText().toString());
//        intentGeneralShow.putExtra("getPlace", setPlace.getText().toString());
//        intentGeneralShow.putExtra("getCity", setCity.getText().toString());
//
//        intentGeneralShow.putExtra("Q1", textQ1);
//        intentGeneralShow.putExtra("Q2", textQ3);
//        intentGeneralShow.putExtra("Q3", textQ2);
//
//        startActivity(intentGeneralShow);
//
//    }

    /** Конец Отключено **/

}
