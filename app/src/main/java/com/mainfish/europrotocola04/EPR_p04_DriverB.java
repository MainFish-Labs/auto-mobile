package com.mainfish.europrotocola04;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.mediation.customevent.CustomEventAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by artli on 05.03.2016.
 */
public class EPR_p04_DriverB extends AppCompatActivity {

	/** Переменные полей ввода */

	public ImageView drB_41_HitBMP;

	public EditText checkID;
	public EditText drB_01_Surname;
	public EditText drB_02_Name;
	public EditText drB_03_Fathername;
	public EditText drB_04_Full;
	public EditText drB_05_Adress;
	public EditText drB_06_Index;
	public EditText drB_08_Phone;
	public EditText drB_09_EMail;
	public EditText drB_10_Model;
	public EditText drB_11_RegSign;
	public EditText drB_14_RegSign_Pricep;
	public EditText drB_21_Seria;
	public EditText drB_22_N;
	public EditText drB_23_From;
	public EditText drB_24_To;
	public EditText drB_26_DocTel;
	public EditText drB_28_Surname_Lic;
	public EditText drB_29_Name_Lic;
	public EditText drB_30_FatherName_Lic;
	public EditText drB_31_BirthDay_Lic;
	public EditText drB_32_Adress_Lic;
	public EditText drB_33_Index_Lic;
	public EditText drB_35_Phone_Lic;
	public EditText drB_36_EMail_Lic;
	public EditText drB_37_Series_Lic;
	public EditText drB_38_Num_Lic;
	public EditText drB_39_Category_Lic;
	public EditText drB_40_Valid_Lic;
	public EditText drB_42_damage;

	public RadioButton drB_18_IR1;
	public RadioButton drB_19_IR2;
	public RadioButton drB_20_IR3;
	public RadioGroup drB_17_InsRadio;

	public Spinner drB_07_Country;
	public Spinner drB_12_RegCountry;
	public Spinner drB_15_RegCountry_Pricep;
	public Spinner drB_25_DocCountry;
	public Spinner drB_34_Country_Lic;

	public SwitchCompat drB_13_Switch_Pricep;
	public SwitchCompat drB_27_Switch_Ins;

	public TextView drB_16_InsName;

	public int total = 40;

	/** Переменные базы данных */

	public Cursor t3_cursor; // !!!!

	public String yes = "Да", no = "Нет", empty = "";

	private EPR_system_DataBaseContainer mDataBase;
	private SQLiteDatabase dbDriverB;
	private String db_driverB, db_driverB_name = "t3_driverB";
	private TextView previewDriverB, closePreview;
	private ScrollView previewScroll;
	private ImageView previewImg;
	private TableLayout previewTable;
	private TableRow previewRow1, previewRow2;

	public String drB_s00_IDA,
			drB_s01_Surname,
			drB_s02_Name,
			drB_s03_Fathername,
			drB_s04_Full,
			drB_s05_Adress,
			drB_s06_Index,
			drB_s07_Country,
			drB_s08_Phone,
			drB_s09_EMail,
			drB_s10_Model,
			drB_s11_RegSign,
			drB_s12_RegCountry,
			drB_s13_Switch_Pricep,
			drB_s14_RegSign_Pricep,
			drB_s15_RegCountry_Pricep,
			drB_s16_InsName,
			drB_s17_InsRadio,
			drB_s18_IR1 = "страховое свидетельство",
			drB_s19_IR2 = "полис",
			drB_s20_IR3 = "сертификат",
			drB_s21_Seria,
			drB_s22_N,
			drB_s23_From,
			drB_s24_To,
			drB_s25_DocCountry,
			drB_s26_DocTel,
			drB_s27_Switch_Ins,
			drB_s28_Surname_Lic,
			drB_s29_Name_Lic,
			drB_s30_FatherName_Lic,
			drB_s31_BirthDay_Lic,
			drB_s32_Adress_Lic,
			drB_s33_Index_Lic,
			drB_s34_Country_Lic,
			drB_s35_Phone_Lic,
			drB_s36_EMail_Lic,
			drB_s37_Series_Lic,
			drB_s38_Num_Lic,
			drB_s39_Category_Lic,
			drB_s40_Valid_Lic,
			drB_s41_HitBMP,
			drB_s42_damage;

	public String[] valuesDrB_temp = new String[total];

	public File Path_sys = android.os.Environment.getExternalStorageDirectory();
	public File dataCheckFile,dataCheckAll;
	public String dataCheckNameDrB = "/db_drB.exist";
	public String dataCheckNameAll = "/db.exist";
	public String dataCheckPath = "/am_cache";
	public String Path = Path_sys + dataCheckPath;

    public TextView InsChooseB;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.epr_p04_driver_b);

	    mContext = EPR_p04_DriverB.this;

	    mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);
	    dbDriverB = mDataBase.getWritableDatabase();

	    dataCheckAll = new File(Path, dataCheckNameAll);
	    dataCheckFile = new File(Path, dataCheckNameDrB);

	    setFields();

	    setUpDrawers();
	    handleSwitchesDriverB();
	    handleSpinnersDriverB();
	    handleRadioDriverB();
	    handleHitB();


        if (savedInstanceState == null) {
            chooseIns(0);

            String Chosen = "";

            InsChooseB = (TextView) findViewById(R.id.driver_b_insurance_name);
            InsChooseB.setText(Chosen);
        }

	    if (dataCheckFile.exists() && dataCheckAll.exists()) {
		    getCursor();
	    }

    }

	/** Инициализация полей ввода */

	public void setFields () {

		drB_41_HitBMP = (ImageView) findViewById(R.id.draw_hit_B);
		checkID = (EditText) findViewById(R.id.buttonID_B);
		drB_01_Surname = (EditText) findViewById(R.id.driver_b_surname);
		drB_02_Name = (EditText) findViewById(R.id.driver_b_name);
		drB_03_Fathername = (EditText) findViewById(R.id.driver_b_fathername);
		drB_04_Full = (EditText) findViewById(R.id.driver_b_full);
		drB_05_Adress = (EditText) findViewById(R.id.driver_b_adress);
		drB_06_Index = (EditText) findViewById(R.id.driver_b_index);
		drB_08_Phone = (EditText) findViewById(R.id.driver_b_phone);
		drB_09_EMail = (EditText) findViewById(R.id.driver_b_email);
		drB_10_Model = (EditText) findViewById(R.id.driver_b_model);
		drB_11_RegSign = (EditText) findViewById(R.id.driver_b_regsign);
		drB_14_RegSign_Pricep = (EditText) findViewById(R.id.driver_b_regsign_pricep);
		drB_21_Seria = (EditText) findViewById(R.id.driver_b_seria);
		drB_22_N = (EditText) findViewById(R.id.driver_b_N);
		drB_23_From = (EditText) findViewById(R.id.driver_b_form);
		drB_24_To = (EditText) findViewById(R.id.driver_b_to);
		drB_26_DocTel = (EditText) findViewById(R.id.driver_b_doc_tel);
		drB_28_Surname_Lic = (EditText) findViewById(R.id.driver_b_lic_surname);
		drB_29_Name_Lic = (EditText) findViewById(R.id.driver_b_lic_name);
		drB_30_FatherName_Lic = (EditText) findViewById(R.id.driver_b_lic_fathername);
		drB_31_BirthDay_Lic = (EditText) findViewById(R.id.driver_b_lic_birthday);
		drB_32_Adress_Lic = (EditText) findViewById(R.id.driver_b_lic_adress);
		drB_33_Index_Lic = (EditText) findViewById(R.id.driver_b_lic_index);
		drB_35_Phone_Lic = (EditText) findViewById(R.id.driver_b_lic_phone);
		drB_36_EMail_Lic = (EditText) findViewById(R.id.driver_b_lic_email);
		drB_37_Series_Lic = (EditText) findViewById(R.id.driver_b_lic_series);
		drB_38_Num_Lic = (EditText) findViewById(R.id.driver_b_lic_num);
		drB_39_Category_Lic = (EditText) findViewById(R.id.driver_b_lic_category);
		drB_40_Valid_Lic = (EditText) findViewById(R.id.driver_b_lic_valid);
		drB_42_damage = (EditText) findViewById(R.id.driver_b_damage);
		drB_18_IR1 = (RadioButton) findViewById(R.id.driver_b_radio_1);
		drB_19_IR2 = (RadioButton) findViewById(R.id.driver_b_radio_2);
		drB_20_IR3 = (RadioButton) findViewById(R.id.driver_b_radio_3);
		drB_17_InsRadio = (RadioGroup) findViewById(R.id.driver_b_radio_ins);
		drB_07_Country = (Spinner) findViewById(R.id.driver_b_country);
		drB_12_RegCountry = (Spinner) findViewById(R.id.driver_b_regcountry);
		drB_15_RegCountry_Pricep = (Spinner) findViewById(R.id.driver_b_regcountry_pricep);
		drB_25_DocCountry = (Spinner) findViewById(R.id.driver_b_doc_country);
		drB_34_Country_Lic = (Spinner) findViewById(R.id.driver_b_lic_country);
		drB_13_Switch_Pricep = (SwitchCompat) findViewById(R.id.driver_b_switch_pricep);
		drB_27_Switch_Ins = (SwitchCompat) findViewById(R.id.driver_b_switch_insured);
		drB_16_InsName = (TextView) findViewById(R.id.driver_b_insurance_name);

	}


	/** Обработка свитчей */

	private int counter;

	public void handleSwitchesDriverB () {

		if (!dataCheckFile.exists()) {
			drB_s13_Switch_Pricep = empty;
			drB_s27_Switch_Ins = empty;
		}

		SwitchCompat[] controlQ = {
				drB_13_Switch_Pricep,
				drB_27_Switch_Ins
		};

		final String[] textQ = {
				"Прицеп - ",
				"ТС застраховано? - "
		};

		final String[] answerQ = {
				no,
				no
		};

		for (counter=0; counter < textQ.length; counter++) {

			controlQ[counter].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				int j = counter;
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Toast.makeText(EPR_p04_DriverB.this, (isChecked ? textQ[j] + yes : textQ[j] + no),Toast.LENGTH_SHORT).show();

					if (isChecked) {
						answerQ[j] = yes;
					} else {
						answerQ[j] = no;
					}

					drB_s13_Switch_Pricep = answerQ[0];
					drB_s27_Switch_Ins = answerQ[1];

				}
			});

		}

	}


	/** Обработка спинеров */

	public String chosen, current;
	private int selected;

	public void handleSpinnersDriverB () {

		if (!dataCheckFile.exists()) {
			drB_s07_Country = "Беларусь";
			drB_s12_RegCountry = "Беларусь";
			drB_s15_RegCountry_Pricep = "Беларусь";
			drB_s25_DocCountry = "Беларусь";
			drB_s34_Country_Lic = "Беларусь";
		}

		final String[] spinners = {
				drB_s07_Country,
				drB_s12_RegCountry,
				drB_s15_RegCountry_Pricep,
				drB_s25_DocCountry,
				drB_s34_Country_Lic
		};

		final Spinner[] countries = {
				(Spinner) findViewById(R.id.driver_b_country),
				(Spinner) findViewById(R.id.driver_b_regcountry),
				(Spinner) findViewById(R.id.driver_b_regcountry_pricep),
				(Spinner) findViewById(R.id.driver_b_doc_country),
				(Spinner) findViewById(R.id.driver_b_lic_country)
		};

		final String[] choose = getResources().getStringArray(R.array.countries_array_full);

//		EPR_system_CustomAdapter adapter = new EPR_system_CustomAdapter(this, android.R.layout.simple_spinner_item, choose);

		for (int i=0; i < countries.length; i++) {

//			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//			countries[i].setAdapter(adapter);

			chosen = countries[i].getSelectedItem().toString();
			current = spinners[i];

			if (chosen != current) {
				countries[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
					                           View itemSelected, int selectedItemPosition, long selectedId) {
//						Toast toast = Toast.makeText(getApplicationContext(),
//								"Ваш выбор: " + choose[selectedItemPosition], Toast.LENGTH_SHORT);
//						toast.show();
						chosen = choose[selectedItemPosition];
						selected = selectedItemPosition;
					}
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
//						EPR_system_CustomAdapter.flag = true;
					}
				});
			}
			countries[i].setSelection(selected);
		}

		drB_s07_Country = countries[0].getSelectedItem().toString();
		drB_s12_RegCountry = countries[1].getSelectedItem().toString();
		drB_s15_RegCountry_Pricep = countries[2].getSelectedItem().toString();
		drB_s25_DocCountry = countries[3].getSelectedItem().toString();
		drB_s34_Country_Lic = countries[4].getSelectedItem().toString();

	}

	/** Обработка RadioGroup */

	public void handleRadioDriverB () {

		if (!dataCheckFile.exists()) {

			drB_17_InsRadio.clearCheck();
			drB_s17_InsRadio = empty;

		}

		drB_17_InsRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
					case -1:
						drB_s17_InsRadio = empty;
						break;
					case R.id.driver_b_radio_1:
						drB_s17_InsRadio = drB_s18_IR1;
						break;
					case R.id.driver_b_radio_2:
						drB_s17_InsRadio = drB_s19_IR2;
						break;
					case R.id.driver_b_radio_3:
						drB_s17_InsRadio = drB_s20_IR3;
						break;
				}
			}
		});

	}

	/** Обработка Места удара */

	public void handleHitB () {

		if (!dataCheckFile.exists()) {
			drB_41_HitBMP.setImageResource(R.drawable.logo);
		}
		drB_41_HitBMP.setImageResource(R.drawable.logo); /** После добавления модуля рисования - исправить */

	}

	/** Конец Обработка **/



	/** Вставка сохранённых значений **/

	private String[] inputDrB_temp;

	public void getCursor () {

		new CustomClass(EPR_p04_DriverB.this);

		inputDrB_temp = new String[total];

		String[] t3 = new String[]{
				EPR_system_DataBaseContainer.T3_ID,
				EPR_system_DataBaseContainer.T3_SURNAME,
				EPR_system_DataBaseContainer.T3_NAME,
				EPR_system_DataBaseContainer.T3_FATHERNAME,
				EPR_system_DataBaseContainer.T3_FULL,
				EPR_system_DataBaseContainer.T3_ADRESS,
				EPR_system_DataBaseContainer.T3_INDEX,
				EPR_system_DataBaseContainer.T3_COUNTRY,
				EPR_system_DataBaseContainer.T3_PHONE,
				EPR_system_DataBaseContainer.T3_EMAIL,
				EPR_system_DataBaseContainer.T3_MODEL,
				EPR_system_DataBaseContainer.T3_REGSIGN,
				EPR_system_DataBaseContainer.T3_REGCOUNTRY,
				EPR_system_DataBaseContainer.T3_SWITCH_PRICEP,
				EPR_system_DataBaseContainer.T3_REGSIGN_PRICEP,
				EPR_system_DataBaseContainer.T3_REGCOUNTRY_PRICEP,
				EPR_system_DataBaseContainer.T3_INSNAME,
				EPR_system_DataBaseContainer.T3_INSRADIO,
				EPR_system_DataBaseContainer.T3_SERIA,
				EPR_system_DataBaseContainer.T3_N,
				EPR_system_DataBaseContainer.T3_FROM,
				EPR_system_DataBaseContainer.T3_TO,
				EPR_system_DataBaseContainer.T3_DOCCOUNTRY,
				EPR_system_DataBaseContainer.T3_DOCTEL,
				EPR_system_DataBaseContainer.T3_SWITCH_INS,
				EPR_system_DataBaseContainer.T3_SURNAME_LIC,
				EPR_system_DataBaseContainer.T3_NAME_LIC,
				EPR_system_DataBaseContainer.T3_FATHERNAME_LIC,
				EPR_system_DataBaseContainer.T3_BIRTHDAY_LIC,
				EPR_system_DataBaseContainer.T3_ADRESS_LIC,
				EPR_system_DataBaseContainer.T3_INDEX_LIC,
				EPR_system_DataBaseContainer.T3_COUNTRY_LIC,
				EPR_system_DataBaseContainer.T3_PHONE_LIC,
				EPR_system_DataBaseContainer.T3_EMAIL_LIC,
				EPR_system_DataBaseContainer.T3_SERIES_LIC,
				EPR_system_DataBaseContainer.T3_NUM_LIC,
				EPR_system_DataBaseContainer.T3_CATEGORY_LIC,
				EPR_system_DataBaseContainer.T3_VALID_LIC,
				EPR_system_DataBaseContainer.T3_HITBMP,
				EPR_system_DataBaseContainer.T3_DAMAGE

		};

		t3_cursor = dbDriverB.query(db_driverB_name, t3,
				null, null, null, null, null);

		t3_cursor.moveToFirst();
		if (t3_cursor != null && t3_cursor.moveToFirst()) {

			inputDrB_temp = new String[] {
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_ID)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_NAME)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_SURNAME)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_FATHERNAME)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_FULL)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_ADRESS)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_INDEX)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_COUNTRY)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_PHONE)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_EMAIL)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_MODEL)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_REGSIGN)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_REGCOUNTRY)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_SWITCH_PRICEP)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_REGSIGN_PRICEP)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_REGCOUNTRY_PRICEP)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_INSNAME)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_INSRADIO)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_SERIA)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_N)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_FROM)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_TO)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_DOCCOUNTRY)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_DOCTEL)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_SWITCH_INS)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_SURNAME_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_NAME_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_FATHERNAME_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_BIRTHDAY_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_ADRESS_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_INDEX_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_COUNTRY_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_PHONE_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_EMAIL_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_SERIES_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_NUM_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_CATEGORY_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_VALID_LIC)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_HITBMP)),
					t3_cursor.getString(t3_cursor.getColumnIndex(EPR_system_DataBaseContainer.T3_DAMAGE))
			};

		}

		t3_cursor.close();
		t3_cursor.moveToFirst();

		checkID.setText(inputDrB_temp[0], TextView.BufferType.EDITABLE);
		drB_01_Surname.setText(inputDrB_temp[1], TextView.BufferType.EDITABLE);
		drB_02_Name.setText(inputDrB_temp[2], TextView.BufferType.EDITABLE);
		drB_03_Fathername.setText(inputDrB_temp[3], TextView.BufferType.EDITABLE);
		drB_04_Full.setText(inputDrB_temp[4], TextView.BufferType.EDITABLE);
		drB_05_Adress.setText(inputDrB_temp[5], TextView.BufferType.EDITABLE);
		drB_06_Index.setText(inputDrB_temp[6], TextView.BufferType.EDITABLE);

		drB_08_Phone.setText(inputDrB_temp[8], TextView.BufferType.EDITABLE);
		drB_09_EMail.setText(inputDrB_temp[9], TextView.BufferType.EDITABLE);
		drB_10_Model.setText(inputDrB_temp[10], TextView.BufferType.EDITABLE);
		drB_11_RegSign.setText(inputDrB_temp[11], TextView.BufferType.EDITABLE);

		drB_s13_Switch_Pricep = inputDrB_temp[13];
		drB_14_RegSign_Pricep.setText(inputDrB_temp[14], TextView.BufferType.EDITABLE);

		drB_16_InsName.setText(inputDrB_temp[16], TextView.BufferType.EDITABLE);

		drB_21_Seria.setText(inputDrB_temp[18], TextView.BufferType.EDITABLE);
		drB_22_N.setText(inputDrB_temp[19], TextView.BufferType.EDITABLE);
		drB_23_From.setText(inputDrB_temp[20], TextView.BufferType.EDITABLE);
		drB_24_To.setText(inputDrB_temp[21], TextView.BufferType.EDITABLE);

		drB_26_DocTel.setText(inputDrB_temp[23], TextView.BufferType.EDITABLE);
		drB_s27_Switch_Ins = inputDrB_temp[24];
		drB_28_Surname_Lic.setText(inputDrB_temp[25], TextView.BufferType.EDITABLE);
		drB_29_Name_Lic.setText(inputDrB_temp[26], TextView.BufferType.EDITABLE);
		drB_30_FatherName_Lic.setText(inputDrB_temp[27], TextView.BufferType.EDITABLE);
		drB_31_BirthDay_Lic.setText(inputDrB_temp[28], TextView.BufferType.EDITABLE);
		drB_32_Adress_Lic.setText(inputDrB_temp[29], TextView.BufferType.EDITABLE);
		drB_33_Index_Lic.setText(inputDrB_temp[30], TextView.BufferType.EDITABLE);

		drB_35_Phone_Lic.setText(inputDrB_temp[32], TextView.BufferType.EDITABLE);
		drB_36_EMail_Lic.setText(inputDrB_temp[33], TextView.BufferType.EDITABLE);
		drB_37_Series_Lic.setText(inputDrB_temp[34], TextView.BufferType.EDITABLE);
		drB_38_Num_Lic.setText(inputDrB_temp[35], TextView.BufferType.EDITABLE);
		drB_39_Category_Lic.setText(inputDrB_temp[36], TextView.BufferType.EDITABLE);
		drB_40_Valid_Lic.setText(inputDrB_temp[37], TextView.BufferType.EDITABLE);

		drB_42_damage.setText(inputDrB_temp[39], TextView.BufferType.EDITABLE);

		drB_41_HitBMP.setImageDrawable(Drawable.createFromPath(drB_s41_HitBMP));

		String[] countries_array = mContext.getResources().getStringArray(R.array.countries_array_full);

		for (int i=0; i < countries_array.length; i++) {
			if (countries_array[i] == drB_s07_Country) {
				drB_07_Country.setSelection(i);
			}
			if (countries_array[i] == drB_s12_RegCountry) {
				drB_12_RegCountry.setSelection(i);
			}
			if (countries_array[i] == drB_s15_RegCountry_Pricep) {
				drB_15_RegCountry_Pricep.setSelection(i);
			}
			if (countries_array[i] == drB_s25_DocCountry) {
				drB_25_DocCountry.setSelection(i);
			}
			if (countries_array[i] == drB_s34_Country_Lic) {
				drB_34_Country_Lic.setSelection(i);
			}
		}

		String[] polis_array = {
				drB_s18_IR1,
				drB_s19_IR2,
				drB_s20_IR3
		};

		RadioButton[] radio_array = {
				drB_18_IR1,
				drB_19_IR2,
				drB_20_IR3
		};

		for (int i=0; i < polis_array.length; i++) {
			if (drB_s17_InsRadio == polis_array[i]) {
				radio_array[i].setChecked(true);
			}
		}

		boolean[] checkBool = new boolean[2];

		checkBool[0] = inputDrB_temp[13] == yes;
		checkBool[1] = inputDrB_temp[24] == yes;


		if (checkBool[0]) {
			drB_13_Switch_Pricep.setChecked(checkBool[0]);
		}
		if (checkBool[1]) {
			drB_27_Switch_Ins.setChecked(checkBool[1]);
		}

	}

	/** Конец Вставка сохранённых значений **/

	/** Обработка базы данных */

	public void getStrings () {

		valuesDrB_temp = new String[]{
				checkID.getText().toString(),
				drB_01_Surname.getText().toString(),
				drB_02_Name.getText().toString(),
				drB_03_Fathername.getText().toString(),
				drB_04_Full.getText().toString(),
				drB_05_Adress.getText().toString(),
				drB_06_Index.getText().toString(),
				drB_s07_Country,
				drB_08_Phone.getText().toString(),
				drB_09_EMail.getText().toString(),
				drB_10_Model.getText().toString(),
				drB_11_RegSign.getText().toString(),
				drB_s12_RegCountry,
				drB_13_Switch_Pricep.getText().toString(),
				drB_14_RegSign_Pricep.getText().toString(),
				drB_s15_RegCountry_Pricep,
				drB_16_InsName.getText().toString(),
				drB_s17_InsRadio,
				drB_21_Seria.getText().toString(),
				drB_22_N.getText().toString(),
				drB_23_From.getText().toString(),
				drB_24_To.getText().toString(),
				drB_s25_DocCountry,
				drB_26_DocTel.getText().toString(),
				drB_27_Switch_Ins.getText().toString(),
				drB_28_Surname_Lic.getText().toString(),
				drB_29_Name_Lic.getText().toString(),
				drB_30_FatherName_Lic.getText().toString(),
				drB_31_BirthDay_Lic.getText().toString(),
				drB_32_Adress_Lic.getText().toString(),
				drB_33_Index_Lic.getText().toString(),
				drB_s34_Country_Lic,
				drB_35_Phone_Lic.getText().toString(),
				drB_36_EMail_Lic.getText().toString(),
				drB_37_Series_Lic.getText().toString(),
				drB_38_Num_Lic.getText().toString(),
				drB_39_Category_Lic.getText().toString(),
				drB_40_Valid_Lic.getText().toString(),
				drB_s41_HitBMP,
				drB_42_damage.getText().toString()
		};

		for (int i=0; i < valuesDrB_temp.length; i++) {
			if (valuesDrB_temp[i] == null) {
				valuesDrB_temp[i] = "";
			}
		}

		drB_s00_IDA = valuesDrB_temp[0];
		drB_s01_Surname = valuesDrB_temp[1];
		drB_s02_Name = valuesDrB_temp[2];
		drB_s03_Fathername = valuesDrB_temp[3];
		drB_s04_Full = valuesDrB_temp[4];
		drB_s05_Adress = valuesDrB_temp[5];
		drB_s06_Index = valuesDrB_temp[6];
		drB_s07_Country = valuesDrB_temp[7];
		drB_s08_Phone = valuesDrB_temp[8];
		drB_s09_EMail = valuesDrB_temp[9];
		drB_s10_Model = valuesDrB_temp[10];
		drB_s11_RegSign = valuesDrB_temp[11];
		drB_s12_RegCountry = valuesDrB_temp[12];
		drB_s13_Switch_Pricep = valuesDrB_temp[13];
		drB_s14_RegSign_Pricep = valuesDrB_temp[14];
		drB_s15_RegCountry_Pricep = valuesDrB_temp[15];
		drB_s16_InsName = valuesDrB_temp[16];
		drB_s17_InsRadio = valuesDrB_temp[17];
		drB_s21_Seria = valuesDrB_temp[18];
		drB_s22_N = valuesDrB_temp[19];
		drB_s23_From = valuesDrB_temp[20];
		drB_s24_To = valuesDrB_temp[21];
		drB_s25_DocCountry = valuesDrB_temp[22];
		drB_s26_DocTel = valuesDrB_temp[23];
		drB_s27_Switch_Ins = valuesDrB_temp[24];
		drB_s28_Surname_Lic = valuesDrB_temp[25];
		drB_s29_Name_Lic = valuesDrB_temp[26];
		drB_s30_FatherName_Lic = valuesDrB_temp[27];
		drB_s31_BirthDay_Lic = valuesDrB_temp[28];
		drB_s32_Adress_Lic = valuesDrB_temp[29];
		drB_s33_Index_Lic = valuesDrB_temp[30];
		drB_s34_Country_Lic = valuesDrB_temp[31];
		drB_s35_Phone_Lic = valuesDrB_temp[32];
		drB_s36_EMail_Lic = valuesDrB_temp[33];
		drB_s37_Series_Lic = valuesDrB_temp[34];
		drB_s38_Num_Lic = valuesDrB_temp[35];
		drB_s39_Category_Lic = valuesDrB_temp[36];
		drB_s40_Valid_Lic = valuesDrB_temp[37];
		drB_s41_HitBMP = valuesDrB_temp[38];
		drB_s42_damage = valuesDrB_temp[39];

	}

	public void getBase () {

		getStrings();

		ContentValues t3_values = new ContentValues();

		// Значения столбцов
		t3_values.put(EPR_system_DataBaseContainer.T3_ID, drB_s00_IDA);
		t3_values.put(EPR_system_DataBaseContainer.T3_SURNAME, drB_s01_Surname);
		t3_values.put(EPR_system_DataBaseContainer.T3_NAME, drB_s02_Name);
		t3_values.put(EPR_system_DataBaseContainer.T3_FATHERNAME, drB_s03_Fathername);
		t3_values.put(EPR_system_DataBaseContainer.T3_FULL, drB_s04_Full);
		t3_values.put(EPR_system_DataBaseContainer.T3_ADRESS, drB_s05_Adress);
		t3_values.put(EPR_system_DataBaseContainer.T3_INDEX, drB_s06_Index);
		t3_values.put(EPR_system_DataBaseContainer.T3_COUNTRY, drB_s07_Country);
		t3_values.put(EPR_system_DataBaseContainer.T3_PHONE, drB_s08_Phone);
		t3_values.put(EPR_system_DataBaseContainer.T3_EMAIL, drB_s09_EMail);
		t3_values.put(EPR_system_DataBaseContainer.T3_MODEL, drB_s10_Model);
		t3_values.put(EPR_system_DataBaseContainer.T3_REGSIGN, drB_s11_RegSign);
		t3_values.put(EPR_system_DataBaseContainer.T3_REGCOUNTRY, drB_s12_RegCountry);
		t3_values.put(EPR_system_DataBaseContainer.T3_SWITCH_PRICEP, drB_s13_Switch_Pricep);
		t3_values.put(EPR_system_DataBaseContainer.T3_REGSIGN_PRICEP, drB_s14_RegSign_Pricep);
		t3_values.put(EPR_system_DataBaseContainer.T3_REGCOUNTRY_PRICEP, drB_s15_RegCountry_Pricep);
		t3_values.put(EPR_system_DataBaseContainer.T3_INSNAME, drB_s16_InsName);
		t3_values.put(EPR_system_DataBaseContainer.T3_INSRADIO, drB_s17_InsRadio);
		t3_values.put(EPR_system_DataBaseContainer.T3_SERIA, drB_s21_Seria);
		t3_values.put(EPR_system_DataBaseContainer.T3_N, drB_s22_N);
		t3_values.put(EPR_system_DataBaseContainer.T3_FROM, drB_s23_From);
		t3_values.put(EPR_system_DataBaseContainer.T3_TO, drB_s24_To);
		t3_values.put(EPR_system_DataBaseContainer.T3_DOCCOUNTRY, drB_s25_DocCountry);
		t3_values.put(EPR_system_DataBaseContainer.T3_DOCTEL, drB_s26_DocTel);
		t3_values.put(EPR_system_DataBaseContainer.T3_SWITCH_INS, drB_s27_Switch_Ins);
		t3_values.put(EPR_system_DataBaseContainer.T3_SURNAME_LIC, drB_s28_Surname_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_NAME_LIC, drB_s29_Name_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_FATHERNAME_LIC, drB_s30_FatherName_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_BIRTHDAY_LIC, drB_s31_BirthDay_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_ADRESS_LIC, drB_s32_Adress_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_INDEX_LIC, drB_s33_Index_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_COUNTRY_LIC, drB_s34_Country_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_PHONE_LIC, drB_s35_Phone_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_EMAIL_LIC, drB_s36_EMail_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_SERIES_LIC, drB_s37_Series_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_NUM_LIC, drB_s38_Num_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_CATEGORY_LIC, drB_s39_Category_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_VALID_LIC, drB_s40_Valid_Lic);
		t3_values.put(EPR_system_DataBaseContainer.T3_HITBMP, drB_s41_HitBMP);
		t3_values.put(EPR_system_DataBaseContainer.T3_DAMAGE, drB_s42_damage);

		// Вставляем данные в таблицу

		if (dataCheckFile.exists() && dataCheckAll.exists()) {
			dbDriverB.update(
					db_driverB_name,
					t3_values,
					EPR_system_DataBaseContainer.T3_ID + "= ? OR " +
							EPR_system_DataBaseContainer.T3_SURNAME + "= ? OR " +
							EPR_system_DataBaseContainer.T3_NAME + "= ? OR " +
							EPR_system_DataBaseContainer.T3_FATHERNAME + "= ? OR " +
							EPR_system_DataBaseContainer.T3_FULL + "= ? OR " +
							EPR_system_DataBaseContainer.T3_ADRESS + "= ? OR " +
							EPR_system_DataBaseContainer.T3_INDEX + "= ? OR " +
							EPR_system_DataBaseContainer.T3_COUNTRY + "= ? OR " +
							EPR_system_DataBaseContainer.T3_PHONE + "= ? OR " +
							EPR_system_DataBaseContainer.T3_EMAIL + "= ? OR " +
							EPR_system_DataBaseContainer.T3_MODEL + "= ? OR " +
							EPR_system_DataBaseContainer.T3_REGSIGN + "= ? OR " +
							EPR_system_DataBaseContainer.T3_REGCOUNTRY + "= ? OR " +
							EPR_system_DataBaseContainer.T3_SWITCH_PRICEP + "= ? OR " +
							EPR_system_DataBaseContainer.T3_REGSIGN_PRICEP + "= ? OR " +
							EPR_system_DataBaseContainer.T3_REGCOUNTRY_PRICEP + "= ? OR " +
							EPR_system_DataBaseContainer.T3_INSNAME + "= ? OR " +
							EPR_system_DataBaseContainer.T3_INSRADIO + "= ? OR " +
							EPR_system_DataBaseContainer.T3_SERIA + "= ? OR " +
							EPR_system_DataBaseContainer.T3_N + "= ? OR " +
							EPR_system_DataBaseContainer.T3_FROM + "= ? OR " +
							EPR_system_DataBaseContainer.T3_TO + "= ? OR " +
							EPR_system_DataBaseContainer.T3_DOCCOUNTRY + "= ? OR " +
							EPR_system_DataBaseContainer.T3_DOCTEL + "= ? OR " +
							EPR_system_DataBaseContainer.T3_SWITCH_INS + "= ? OR " +
							EPR_system_DataBaseContainer.T3_SURNAME_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_NAME_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_FATHERNAME_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_BIRTHDAY_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_ADRESS_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_INDEX_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_COUNTRY_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_PHONE_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_EMAIL_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_SERIES_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_NUM_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_CATEGORY_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_VALID_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T3_HITBMP + "= ? OR " +
							EPR_system_DataBaseContainer.T3_DAMAGE + "= ?",
					new String[]{
							drB_s00_IDA,
							drB_s01_Surname,
							drB_s02_Name,
							drB_s03_Fathername,
							drB_s04_Full,
							drB_s05_Adress,
							drB_s06_Index,
							drB_s07_Country,
							drB_s08_Phone,
							drB_s09_EMail,
							drB_s10_Model,
							drB_s11_RegSign,
							drB_s12_RegCountry,
							drB_s13_Switch_Pricep,
							drB_s14_RegSign_Pricep,
							drB_s15_RegCountry_Pricep,
							drB_s16_InsName,
							drB_s17_InsRadio,
							drB_s21_Seria,
							drB_s22_N,
							drB_s23_From,
							drB_s24_To,
							drB_s25_DocCountry,
							drB_s26_DocTel,
							drB_s27_Switch_Ins,
							drB_s28_Surname_Lic,
							drB_s29_Name_Lic,
							drB_s30_FatherName_Lic,
							drB_s31_BirthDay_Lic,
							drB_s32_Adress_Lic,
							drB_s33_Index_Lic,
							drB_s34_Country_Lic,
							drB_s35_Phone_Lic,
							drB_s36_EMail_Lic,
							drB_s37_Series_Lic,
							drB_s38_Num_Lic,
							drB_s39_Category_Lic,
							drB_s40_Valid_Lic,
							drB_s41_HitBMP,
							drB_s42_damage
					}
			);
		} else {
			dbDriverB.insert(
					db_driverB_name,
					null,
					t3_values
			);
			checkFileCreate();
		}
		
		assert previewDriverB != null;
		db_driverB =
				"ID   " + drB_s00_IDA
						+ "\nФамилия   " + drB_s01_Surname
						+ "\nИмя  " + drB_s02_Name
						+ "\nОтчество   " + drB_s03_Fathername
						+ "\nНаим. юр. лица   " + drB_s04_Full
						+ "\nАдрес   " + drB_s05_Adress
						+ "\nИндекс   " + drB_s06_Index
						+ "\nСтрана   " + drB_s07_Country
						+ "\nТел.   " + drB_s08_Phone
						+ "\ne-mail   " + drB_s09_EMail
						+ "\nМарка, модель   " + drB_s10_Model
						+ "\nРег. знак   " + drB_s11_RegSign
						+ "\nСтрана регистрации   " + drB_s12_RegCountry
						+ "\nПрицеп   " + drB_s13_Switch_Pricep
						+ "\nРег. знак прицепа   " + drB_s14_RegSign_Pricep
						+ "\nСтрана регистрации прицепа   " + drB_s15_RegCountry_Pricep
						+ "\nСтраховая компания   " + drB_s16_InsName
						+ "\nНаим. документа   " + drB_s17_InsRadio
						+ "\nстраховое свидетельство   " + drB_s18_IR1
						+ "\nполис   " + drB_s19_IR2
						+ "\nсертификат   " + drB_s20_IR3
						+ "\nСерия   " + drB_s21_Seria
						+ "\n№   " + drB_s22_N
						+ "\nДействительно с   " + drB_s23_From
						+ "\nпо   " + drB_s24_To
						+ "\nСтрана   " + drB_s25_DocCountry
						+ "\nТел. или e-mail   " + drB_s26_DocTel
						+ "\nЗастраховано?   " + drB_s27_Switch_Ins
						+ "\nФамилия   " + drB_s28_Surname_Lic
						+ "\nИмя   " + drB_s29_Name_Lic
						+ "\nОтчество   " + drB_s30_FatherName_Lic
						+ "\nДата рождения   " + drB_s31_BirthDay_Lic
						+ "\nАдрес   " + drB_s32_Adress_Lic
						+ "\nИндекс   " + drB_s33_Index_Lic
						+ "\nСтрана   " + drB_s34_Country_Lic
						+ "\nТел.   " + drB_s35_Phone_Lic
						+ "\ne-mail   " + drB_s36_EMail_Lic
						+ "\nСерия   " + drB_s37_Series_Lic
						+ "\n№   " + drB_s38_Num_Lic
						+ "\nКатегория   " + drB_s39_Category_Lic
						+ "\nДействительно до   " + drB_s40_Valid_Lic
						+ "\nВидимые поврежд.   " + drB_s42_damage
						+ "\nМесто удара   " + drB_s41_HitBMP;
		
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

	Context mContext;
	public class CustomClass
	{
		Context mContext;
		public CustomClass(Context context) // constructor
		{
			mContext = context;
		}
	}
    
    private String[] infoText, insuranseText, insuranseTextFull, insuranseTextTel, insuranseTextEmail;
    private ListView mDrawerListView, mDrawerListView_insuranse;
    
    public boolean mSlideState, mSlideStateIns;
    private DrawerLayout mDrawerLayout, mDrawerIns;
    
    public void setUpDrawers () {

        infoText = getResources().getStringArray(R.array.info_button3);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.epr_system_info_drawer, infoText));

        insuranseText = getResources().getStringArray(R.array.insuranse_list);
        insuranseTextFull = getResources().getStringArray(R.array.insuranse_list_full);
        insuranseTextTel = getResources().getStringArray(R.array.insuranse_list_tel);
        insuranseTextEmail = getResources().getStringArray(R.array.insuranse_list_email);
        mDrawerListView_insuranse = (ListView) findViewById(R.id.insuranse_drawer);

        // подключим адаптер для списка
        mDrawerListView_insuranse.setAdapter(new ArrayAdapter<String>(this,
                R.layout.epr_system_insuranse_drawer, insuranseText));

        // установим слушатель для щелчков по элементам списка
        mDrawerListView_insuranse.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_driver_b);
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

        mDrawerIns = (DrawerLayout) findViewById(R.id.drawer_layout_driver_b_ins);
        mSlideStateIns = false;

        mDrawerIns.setDrawerListener(new ActionBarDrawerToggle(this,
                mDrawerIns,
                0,
                0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideStateIns=false;//is Closed
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideStateIns=true;//is Opened
            }});

    }

    //  Слушатель для элементов списка в выдвижной панели
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),
                    "Выбрана компания " + insuranseText[position], Toast.LENGTH_SHORT).show();
            chooseIns(position);
        }
    }

    private void chooseIns(int position) {

        // обновим выбранный элемент списка и закрываем панель
        mDrawerListView_insuranse.setItemChecked(position, true);

        String Chosen = insuranseTextFull[position] + "\n" + insuranseTextTel[position] + "\n" + insuranseTextEmail[position];

        InsChooseB = (TextView) findViewById(R.id.driver_b_insurance_name);
        InsChooseB.setText(Chosen);
        mDrawerIns.closeDrawer(mDrawerListView_insuranse);
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

    public void gotoCircum (View view) {

	    getBase();

        Intent intentCircum = new Intent(this, EPR_p05_Circumstances.class);
        startActivity(intentCircum);

    }

    public void gotoDriverA (View view) {

	    getBase();

        Intent intentDriverA = new Intent(this, EPR_p03_DriverA.class);
        startActivity(intentDriverA);

    }

    public void btnOpenI (View view) {

        if(mSlideState){
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.END);
        }

    }

    public void btnOpenIns (View view) {

        if(mSlideStateIns){
            mDrawerIns.closeDrawer(GravityCompat.START);
        }else{
            mDrawerIns.openDrawer(GravityCompat.START);
        }

    }

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
			mDrawerLayout.closeDrawer(GravityCompat.END);
		} else if (mDrawerIns.isDrawerOpen(GravityCompat.START)) {
			mDrawerIns.closeDrawer(GravityCompat.START);
		} else {
			getBase();
			super.onBackPressed();
		}
	}

}