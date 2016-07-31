package com.mainfish.europrotocola04;

import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by artli on 05.03.2016.
 */

public class General extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

	    setContentView(R.layout.accident_general);

//	    handleDataBaseGeneral();
//
//        setUpInfoDrawer();
//
//        handleSwitchesGeneral();
//
//	    fillStored();

	    getDateTime();

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


//        witList = (TableLayout) findViewById(R.id.wit_list);
//        buttonWit = (TextView) findViewById(R.id.list_create);

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

    }

    /** Конец Дата и время **/



    /** Обработка свитчей */

    public int countQ1 = 0, countQ2 = 0, countQ3 = 0;

    public void handleSwitchesGeneral () {

        final TextView btn_GoToA = (TextView) findViewById(R.id.goto_driverA);
        final TextView btn_Impossible = (TextView) findViewById(R.id.btn_impossible);
        btn_GoToA.setVisibility(View.VISIBLE);
        btn_Impossible.setVisibility(View.GONE);

        SwitchCompat control_question_1 = (SwitchCompat) findViewById(R.id.gen_switch_q1);
        SwitchCompat control_question_2 = (SwitchCompat) findViewById(R.id.gen_switch_q2);
        SwitchCompat control_question_3 = (SwitchCompat) findViewById(R.id.gen_switch_q3);

        assert control_question_1 != null;
        control_question_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(General.this, (isChecked ? "В Вашем случае заполнение Извещения о ДТП невозможно\n" +
                        "(см. стр 1 «Обязательные условия»). Вызывайте ГАИ" : "Пострадавшие люди - " + "Нет"),Toast.LENGTH_LONG).show();

                if (isChecked) {
                    countQ1=1;
                } else {
                    countQ1=0;
                }

                if ((countQ1 == 1) || (countQ2 == 1) || (countQ3 == 1)) {

                    btn_GoToA.setVisibility(View.GONE);
                    btn_Impossible.setVisibility(View.VISIBLE);
                    textQ1 = "Да";

                } else {

                    btn_Impossible.setVisibility(View.GONE);
                    btn_GoToA.setVisibility(View.VISIBLE);
                    textQ1 = "Нет";

                }

            }
        });


        assert control_question_2 != null;
        control_question_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(General.this, (isChecked ? "В Вашем случае заполнение Извещения о ДТП невозможно\n" +
                        "(см. стр 1 «Обязательные условия»). Вызывайте ГАИ" : "Материальный вред прочим ТС - " + "Нет"),Toast.LENGTH_LONG).show();

                if (isChecked) {
                    countQ2=1;
                } else {
                    countQ2=0;
                }

                if ((countQ1 == 1) || (countQ2 == 1) || (countQ3 == 1)) {

                    btn_GoToA.setVisibility(View.GONE);
                    btn_Impossible.setVisibility(View.VISIBLE);
                    textQ2 = "Да";

                } else {

                    btn_Impossible.setVisibility(View.GONE);
                    btn_GoToA.setVisibility(View.VISIBLE);
                    textQ2 = "Нет";

                }

            }
        });


        assert control_question_3 != null;
        control_question_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(General.this, (isChecked ? "В Вашем случае заполнение Извещения о ДТП невозможно\n" +
                        "(см. стр 1 «Обязательные условия»). Вызывайте ГАИ" : "Материальный вред другому имуществу - " + "Нет"),Toast.LENGTH_LONG).show();

                if (isChecked) {
                    countQ3=1;
                } else {
                    countQ3=0;
                }

                if ((countQ1 == 1) || (countQ2 == 1) || (countQ3 == 1)) {

                    btn_GoToA.setVisibility(View.GONE);
                    btn_Impossible.setVisibility(View.VISIBLE);
                    textQ3 = "Да";

                } else {

                    btn_Impossible.setVisibility(View.GONE);
                    btn_GoToA.setVisibility(View.VISIBLE);
                    textQ3 = "Нет";

                }

            }
        });

    }

    /** Конец Обработка свитчей **/



    /** Обработка базы данных */

    public Cursor cursor; // !!!!

    public EditText setDate;
    public EditText setTime;
    public TextView setPlace;
    public EditText setGeo;
    public EditText setCity;
    public SwitchCompat setQ1;
    public SwitchCompat setQ2;
    public SwitchCompat setQ3;
    public EditText Witness1;
    public EditText Witness2;
    public EditText Witness3;
    public EditText Witness4;
    public boolean isDataBase;

    private DataBaseContainer mDataBaseContainer;
    private SQLiteDatabase mSQLiteDatabase;

    public String gen_date = "", gen_date_auto = "", gen_time = "", gen_time_auto = "", gen_country = "", gen_geo = "", gen_city = "";
    public String textQ1 = "Нет", textQ2 = "Нет", textQ3 = "Нет", q_text1 = "Нет", q_text2 = "Нет", q_text3 = "Нет";
    public String wit_text1 = "", wit_text2 = "", wit_text3 = "", wit_text4 = "";

	public String dataCheckPath;
	public File dataCheck;

	public String dataCheckDir = "/am_cache/";
	public String dataCheckFile = dataCheckDir + "base.txt";

	public File sdDir;

    public void handleDataBaseGeneral () {

        setDate = (EditText) findViewById(R.id.gen_input_date);
        setTime = (EditText) findViewById(R.id.gen_input_time);

        setPlace = (TextView) findViewById(R.id.gen_input_country);
        setGeo = (EditText) findViewById(R.id.gen_input_geo);
        setCity = (EditText) findViewById(R.id.gen_input_city);

        setQ1 = (SwitchCompat) findViewById(R.id.gen_switch_q1);
        setQ2 = (SwitchCompat) findViewById(R.id.gen_switch_q2);
        setQ3 = (SwitchCompat) findViewById(R.id.gen_switch_q3);

        Witness1 = (EditText) findViewById(R.id.wit1);
        Witness2 = (EditText) findViewById(R.id.wit2);
        Witness3 = (EditText) findViewById(R.id.wit3);
        Witness4 = (EditText) findViewById(R.id.wit4);

        mDataBaseContainer = new DataBaseContainer(this, "am_protocol_db.db", null, 1);

        mSQLiteDatabase = mDataBaseContainer.getWritableDatabase();

    }
	
	public void handleCheckFile () {
		
		String sdState = android.os.Environment.getExternalStorageState();
		if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			sdDir = android.os.Environment.getExternalStorageDirectory();
			dataCheck = new File(sdDir, dataCheckFile);
			dataCheckPath = sdDir + dataCheckFile;
		} else {
			dataCheck = getApplicationContext().getCacheDir();
		}
		
	}

    public void getStrings () {

        gen_date = setDate.getText().toString();
        gen_time = setTime.getText().toString();
        gen_country = setPlace.getText().toString();
        gen_geo = setGeo.getText().toString();
        gen_city = setCity.getText().toString();

        q_text1 = textQ1;
        q_text2 = textQ2;
        q_text3 = textQ3;

        wit_text1 = Witness1.getText().toString();
        wit_text2 = Witness2.getText().toString();
        wit_text3 = Witness3.getText().toString();
        wit_text4 = Witness4.getText().toString();

    }

    public void t1Value () {
	
	    handleCheckFile();

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

        if (!dataCheck.exists()) {
	        mSQLiteDatabase.insert("am_protocol", null, t1_values);

	        dataCheckStart();
        } else {
	        handleCheckFile();
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

	public void dataCheckStart () {

		if (!dataCheck.exists())
			dataCheck.mkdirs();
		try {
			FileWriter f = new FileWriter(dataCheck);
			f.write("database exist");
			f.flush();
			f.close();
		} catch (Exception e) {
			return;
		}

	}

	public void getCursor () {

		cursor = mSQLiteDatabase.query("am_protocol", new String[] {
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

	}

	public void handleCursor () {

		cursor.moveToFirst();
		if(cursor.moveToFirst() ) {
			gen_date = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_DATE));
			gen_time = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_TIME));
			gen_country = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_COUNTRY));
			gen_geo = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_GEO));
			gen_city = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_M_PLACE));

			q_text1 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_Q1));
			q_text2 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_Q2));
			q_text3 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_Q3));

			wit_text1 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_W1));
			wit_text2 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_W2));
			wit_text3 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_W3));
			wit_text4 = cursor.getString(cursor.getColumnIndex(DataBaseContainer.T1_W4));

			TextView testBaseView = (TextView) findViewById(R.id.testBase);
			assert testBaseView != null;
			String db_general = "Дата ДТП " + gen_date + "\nВремя ДТП " + gen_time + "\nСтрана ДТП " + gen_country + "\nМесто ДПТ " + gen_geo + "\nМесто ДТП " + gen_city + "\nПострадавшие? " + q_text1 + "\nВред транспорту? " + q_text2 + "\nВред имуществу? " + q_text3 + "\nСвидетель 1 " + wit_text1 + "\nСвидетель 2 " + wit_text2 + "\nСвидетель 3 " + wit_text3 + "\nСвидетель 4 " + wit_text4;
			testBaseView.setText(db_general, TextView.BufferType.EDITABLE);

			cursor.close();
			cursor.moveToFirst();

		}

	}

    public void getBase () {

        t1Value();

		if (!isDataBase) {

			getCursor();

			handleCursor();

		}
    }


	/** Конец Обработка базы данных **/



	/** Вставка сохранённых значений **/

	public void fillStored () {

		if (dataCheck == null) {

			getDateTime();

			isDataBase = false;

		} else {

			handleCheckFile();

			setDate.setText(gen_date, TextView.BufferType.EDITABLE);
			setTime.setText(gen_time,TextView.BufferType.EDITABLE);
			setPlace.setText(gen_country, TextView.BufferType.EDITABLE);
			setGeo.setText(gen_geo, TextView.BufferType.EDITABLE);
			setCity.setText(gen_city, TextView.BufferType.EDITABLE);

			Witness1.setText(wit_text1, TextView.BufferType.EDITABLE);
			Witness2.setText(wit_text2, TextView.BufferType.EDITABLE);
			Witness3.setText(wit_text3, TextView.BufferType.EDITABLE);
			Witness4.setText(wit_text4, TextView.BufferType.EDITABLE);

			getCursor();

			handleCursor();

			isDataBase = true;

		}

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

        //mDrawerListView = (ListView) findViewById(R.id.info_drawer);
        //mDrawerListView.setVisibility(View.VISIBLE);

        if(mSlideState){
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.END);
        }

    }

    /** Конец Кнопки **/

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
				return true;

			case R.id.action_clear_db:
				File dbCheck = new File(dataCheckPath);
				File dbSelf = new File("/data/data/com.mainfish.europrotocola04/databases/am_protocol_db.db");
				boolean delCheck = dbCheck.delete();
				boolean delSelf = dbSelf.delete();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

    /** Отключено **/

//    public void createList (View view) {
//
//        witList.setVisibility((witList.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
//
//        if (witList.getVisibility() == View.GONE) {
//
//            String createli = "показать список";
//            buttonWit.setText(createli, TextView.BufferType.EDITABLE);
//
//        } else {
//
//            String createli = "скрыть список";
//            buttonWit.setText(createli, TextView.BufferType.EDITABLE);
//
//        }
//
//    }

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
