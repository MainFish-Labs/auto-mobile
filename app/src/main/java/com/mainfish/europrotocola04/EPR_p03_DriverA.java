package com.mainfish.europrotocola04;

import android.app.ProgressDialog;
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
public class EPR_p03_DriverA extends AppCompatActivity {

	/** Переменные полей ввода */

	public ImageView drA_41_HitBMP;
	
	public EditText checkID;
	public EditText drA_01_Surname;
	public EditText drA_02_Name;
	public EditText drA_03_Fathername;
	public EditText drA_04_Full;
	public EditText drA_05_Adress;
	public EditText drA_06_Index;
	public EditText drA_08_Phone;
	public EditText drA_09_EMail;
	public EditText drA_10_Model;
	public EditText drA_11_RegSign;
	public EditText drA_14_RegSign_Pricep;
	public EditText drA_21_Seria;
	public EditText drA_22_N;
	public EditText drA_23_From;
	public EditText drA_24_To;
	public EditText drA_26_DocTel;
	public EditText drA_28_Surname_Lic;
	public EditText drA_29_Name_Lic;
	public EditText drA_30_FatherName_Lic;
	public EditText drA_31_BirthDay_Lic;
	public EditText drA_32_Adress_Lic;
	public EditText drA_33_Index_Lic;
	public EditText drA_35_Phone_Lic;
	public EditText drA_36_EMail_Lic;
	public EditText drA_37_Series_Lic;
	public EditText drA_38_Num_Lic;
	public EditText drA_39_Category_Lic;
	public EditText drA_40_Valid_Lic;
	public EditText drA_42_damage;

	public RadioButton drA_18_IR1;
	public RadioButton drA_19_IR2;
	public RadioButton drA_20_IR3;
	public RadioGroup drA_17_InsRadio;

	public Spinner drA_07_Country;
	public Spinner drA_12_RegCountry;
	public Spinner drA_15_RegCountry_Pricep;
	public Spinner drA_25_DocCountry;
	public Spinner drA_34_Country_Lic;

	public SwitchCompat drA_13_Switch_Pricep;
	public SwitchCompat drA_27_Switch_Ins;

	public TextView drA_16_InsName;

	public int total = 40;

	/** Переменные базы данных */

	public Cursor t2_cursor; // !!!!

	public String yes = "Да", no = "Нет", empty = "";

	private EPR_system_DataBaseContainer mDataBase;
	private SQLiteDatabase dbDriverA;
	private String db_driverA;
	private TextView previewDriverA, closePreview;
	private ScrollView previewScroll;
	private ImageView previewImg;
	private TableLayout previewTable;
	private TableRow previewRow1, previewRow2;

	public String drA_s00_IDA,
			drA_s01_Surname,
			drA_s02_Name,
			drA_s03_Fathername,
			drA_s04_Full,
			drA_s05_Adress,
			drA_s06_Index,
			drA_s07_Country,
			drA_s08_Phone,
			drA_s09_EMail,
			drA_s10_Model,
			drA_s11_RegSign,
			drA_s12_RegCountry,
			drA_s13_Switch_Pricep,
			drA_s14_RegSign_Pricep,
			drA_s15_RegCountry_Pricep,
			drA_s16_InsName,
			drA_s17_InsRadio,
			drA_s18_IR1 = "страховое свидетельство",
			drA_s19_IR2 = "полис",
			drA_s20_IR3 = "сертификат",
			drA_s21_Seria,
			drA_s22_N,
			drA_s23_From,
			drA_s24_To,
			drA_s25_DocCountry,
			drA_s26_DocTel,
			drA_s27_Switch_Ins,
			drA_s28_Surname_Lic,
			drA_s29_Name_Lic,
			drA_s30_FatherName_Lic,
			drA_s31_BirthDay_Lic,
			drA_s32_Adress_Lic,
			drA_s33_Index_Lic,
			drA_s34_Country_Lic,
			drA_s35_Phone_Lic,
			drA_s36_EMail_Lic,
			drA_s37_Series_Lic,
			drA_s38_Num_Lic,
			drA_s39_Category_Lic,
			drA_s40_Valid_Lic,
			drA_s41_HitBMP,
			drA_s42_damage;

	public String[] valuesDrA_temp = new String[total];

	public File Path_sys = android.os.Environment.getExternalStorageDirectory();
	public File dataCheckFile, dataCheckAll;
	public String dataCheckNameDrA = "/db_drA.exist";
	public String dataCheckNameAll = "/db.exist";
	public String dataCheckPath = "/am_cache";
	public String Path = Path_sys + dataCheckPath;

    public TextView InsChooseA;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.epr_p03_driver_a);

	    mContext = EPR_p03_DriverA.this;

	    mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);
	    dbDriverA = mDataBase.getWritableDatabase();

	    dataCheckAll = new File(Path, dataCheckNameAll);
	    dataCheckFile = new File(Path, dataCheckNameDrA);

	    setFields();

	    setUpDrawers();
	    handleSwitchesDriverA();
	    handleSpinnersDriverA();
	    handleRadioDriverA();
	    handleHitA();

	    if ((savedInstanceState == null) && !dataCheckFile.exists()) {  /** Устанавливает 0, если не выбрана страховая */
		    chooseIns(0);

		    String Chosen = "";

		    InsChooseA = (TextView) findViewById(R.id.driver_a_insurance_name);
		    InsChooseA.setText(Chosen);
	    }
	
	    if (dataCheckFile.exists() && dataCheckAll.exists()) {
		    getCursor();
	    }
	
    }

	/** Инициализация полей ввода */

	public void setFields () {

		drA_41_HitBMP = (ImageView) findViewById(R.id.draw_hit_A);
		checkID = (EditText) findViewById(R.id.buttonID_A);
		drA_01_Surname = (EditText) findViewById(R.id.driver_a_surname);
		drA_02_Name = (EditText) findViewById(R.id.driver_a_name);
		drA_03_Fathername = (EditText) findViewById(R.id.driver_a_fathername);
		drA_04_Full = (EditText) findViewById(R.id.driver_a_full);
		drA_05_Adress = (EditText) findViewById(R.id.driver_a_adress);
		drA_06_Index = (EditText) findViewById(R.id.driver_a_index);
		drA_08_Phone = (EditText) findViewById(R.id.driver_a_phone);
		drA_09_EMail = (EditText) findViewById(R.id.driver_a_email);
		drA_10_Model = (EditText) findViewById(R.id.driver_a_model);
		drA_11_RegSign = (EditText) findViewById(R.id.driver_a_regsign);
		drA_14_RegSign_Pricep = (EditText) findViewById(R.id.driver_a_regsign_pricep);
		drA_21_Seria = (EditText) findViewById(R.id.driver_a_seria);
		drA_22_N = (EditText) findViewById(R.id.driver_a_N);
		drA_23_From = (EditText) findViewById(R.id.driver_a_form);
		drA_24_To = (EditText) findViewById(R.id.driver_a_to);
		drA_26_DocTel = (EditText) findViewById(R.id.driver_a_doc_tel);
		drA_28_Surname_Lic = (EditText) findViewById(R.id.driver_a_lic_surname);
		drA_29_Name_Lic = (EditText) findViewById(R.id.driver_a_lic_name);
		drA_30_FatherName_Lic = (EditText) findViewById(R.id.driver_a_lic_fathername);
		drA_31_BirthDay_Lic = (EditText) findViewById(R.id.driver_a_lic_birthday);
		drA_32_Adress_Lic = (EditText) findViewById(R.id.driver_a_lic_adress);
		drA_33_Index_Lic = (EditText) findViewById(R.id.driver_a_lic_index);
		drA_35_Phone_Lic = (EditText) findViewById(R.id.driver_a_lic_phone);
		drA_36_EMail_Lic = (EditText) findViewById(R.id.driver_a_lic_email);
		drA_37_Series_Lic = (EditText) findViewById(R.id.driver_a_lic_series);
		drA_38_Num_Lic = (EditText) findViewById(R.id.driver_a_lic_num);
		drA_39_Category_Lic = (EditText) findViewById(R.id.driver_a_lic_category);
		drA_40_Valid_Lic = (EditText) findViewById(R.id.driver_a_lic_valid);
		drA_42_damage = (EditText) findViewById(R.id.driver_a_damage);
		drA_18_IR1 = (RadioButton) findViewById(R.id.driver_a_radio_1);
		drA_19_IR2 = (RadioButton) findViewById(R.id.driver_a_radio_2);
		drA_20_IR3 = (RadioButton) findViewById(R.id.driver_a_radio_3);
		drA_17_InsRadio = (RadioGroup) findViewById(R.id.driver_a_radio_ins);
		drA_07_Country = (Spinner) findViewById(R.id.driver_a_country);
		drA_12_RegCountry = (Spinner) findViewById(R.id.driver_a_regcountry);
		drA_15_RegCountry_Pricep = (Spinner) findViewById(R.id.driver_a_regcountry_pricep);
		drA_25_DocCountry = (Spinner) findViewById(R.id.driver_a_doc_country);
		drA_34_Country_Lic = (Spinner) findViewById(R.id.driver_a_lic_country);
		drA_13_Switch_Pricep = (SwitchCompat) findViewById(R.id.driver_a_switch_pricep);
		drA_27_Switch_Ins = (SwitchCompat) findViewById(R.id.driver_a_switch_insured);
		drA_16_InsName = (TextView) findViewById(R.id.driver_a_insurance_name);

	}


	/** Обработка свитчей */

	private int counter;

	public void handleSwitchesDriverA () {

		if (!dataCheckFile.exists()) {
			drA_s13_Switch_Pricep = empty;
			drA_s27_Switch_Ins = empty;
		}

		SwitchCompat[] controlQ = {
				drA_13_Switch_Pricep,
				drA_27_Switch_Ins
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
					Toast.makeText(EPR_p03_DriverA.this, (isChecked ? textQ[j] + yes : textQ[j] + no),Toast.LENGTH_SHORT).show();

					if (isChecked) {
						answerQ[j] = yes;
					} else {
						answerQ[j] = no;
					}

					drA_s13_Switch_Pricep = answerQ[0];
					drA_s27_Switch_Ins = answerQ[1];

				}
			});

		}

	}


	/** Обработка спинеров */

	public String chosen, current;
	private int selected;

	public void handleSpinnersDriverA () {

		if (!dataCheckFile.exists()) {
			drA_s07_Country = "Беларусь";
			drA_s12_RegCountry = "Беларусь";
			drA_s15_RegCountry_Pricep = "Беларусь";
			drA_s25_DocCountry = "Беларусь";
			drA_s34_Country_Lic = "Беларусь";
		}

		final String[] spinners = {
				drA_s07_Country,
				drA_s12_RegCountry,
				drA_s15_RegCountry_Pricep,
				drA_s25_DocCountry,
				drA_s34_Country_Lic
		};

		final Spinner[] countries = {
				(Spinner) findViewById(R.id.driver_a_country),
				(Spinner) findViewById(R.id.driver_a_regcountry),
				(Spinner) findViewById(R.id.driver_a_regcountry_pricep),
				(Spinner) findViewById(R.id.driver_a_doc_country),
				(Spinner) findViewById(R.id.driver_a_lic_country)
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

		drA_s07_Country = countries[0].getSelectedItem().toString();
		drA_s12_RegCountry = countries[1].getSelectedItem().toString();
		drA_s15_RegCountry_Pricep = countries[2].getSelectedItem().toString();
		drA_s25_DocCountry = countries[3].getSelectedItem().toString();
		drA_s34_Country_Lic = countries[4].getSelectedItem().toString();

	}

	/** Обработка RadioGroup */

	public void handleRadioDriverA () {

		if (!dataCheckFile.exists()) {

			drA_17_InsRadio.clearCheck();
			drA_s17_InsRadio = empty;

		}

		drA_17_InsRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
					case -1:
						drA_s17_InsRadio = empty;
						break;
					case R.id.driver_a_radio_1:
						drA_s17_InsRadio = drA_s18_IR1;
						break;
					case R.id.driver_a_radio_2:
						drA_s17_InsRadio = drA_s19_IR2;
						break;
					case R.id.driver_a_radio_3:
						drA_s17_InsRadio = drA_s20_IR3;
						break;
				}
			}
		});

	}

	/** Обработка Места удара */

	public void handleHitA () {

		if (!dataCheckFile.exists()) {
			drA_41_HitBMP.setImageResource(R.drawable.logo);
		}
		drA_41_HitBMP.setImageResource(R.drawable.logo); /** После добавления модуля рисования - исправить */

	}

	/** Конец Обработка **/
	
	
	
	/** Вставка сохранённых значений **/
	
	private String[] inputDrA_temp;
	
	public void getCursor () {

		new CustomClass(EPR_p03_DriverA.this);
		
		inputDrA_temp = new String[total];

		String[] t2 = new String[]{
				EPR_system_DataBaseContainer.T2_ID,
				EPR_system_DataBaseContainer.T2_SURNAME,
				EPR_system_DataBaseContainer.T2_NAME,
				EPR_system_DataBaseContainer.T2_FATHERNAME,
				EPR_system_DataBaseContainer.T2_FULL,
				EPR_system_DataBaseContainer.T2_ADRESS,
				EPR_system_DataBaseContainer.T2_INDEX,
				EPR_system_DataBaseContainer.T2_COUNTRY,
				EPR_system_DataBaseContainer.T2_PHONE,
				EPR_system_DataBaseContainer.T2_EMAIL,
				EPR_system_DataBaseContainer.T2_MODEL,
				EPR_system_DataBaseContainer.T2_REGSIGN,
				EPR_system_DataBaseContainer.T2_REGCOUNTRY,
				EPR_system_DataBaseContainer.T2_SWITCH_PRICEP,
				EPR_system_DataBaseContainer.T2_REGSIGN_PRICEP,
				EPR_system_DataBaseContainer.T2_REGCOUNTRY_PRICEP,
				EPR_system_DataBaseContainer.T2_INSNAME,
				EPR_system_DataBaseContainer.T2_INSRADIO,
				EPR_system_DataBaseContainer.T2_SERIA,
				EPR_system_DataBaseContainer.T2_N,
				EPR_system_DataBaseContainer.T2_FROM,
				EPR_system_DataBaseContainer.T2_TO,
				EPR_system_DataBaseContainer.T2_DOCCOUNTRY,
				EPR_system_DataBaseContainer.T2_DOCTEL,
				EPR_system_DataBaseContainer.T2_SWITCH_INS,
				EPR_system_DataBaseContainer.T2_SURNAME_LIC,
				EPR_system_DataBaseContainer.T2_NAME_LIC,
				EPR_system_DataBaseContainer.T2_FATHERNAME_LIC,
				EPR_system_DataBaseContainer.T2_BIRTHDAY_LIC,
				EPR_system_DataBaseContainer.T2_ADRESS_LIC,
				EPR_system_DataBaseContainer.T2_INDEX_LIC,
				EPR_system_DataBaseContainer.T2_COUNTRY_LIC,
				EPR_system_DataBaseContainer.T2_PHONE_LIC,
				EPR_system_DataBaseContainer.T2_EMAIL_LIC,
				EPR_system_DataBaseContainer.T2_SERIES_LIC,
				EPR_system_DataBaseContainer.T2_NUM_LIC,
				EPR_system_DataBaseContainer.T2_CATEGORY_LIC,
				EPR_system_DataBaseContainer.T2_VALID_LIC,
				EPR_system_DataBaseContainer.T2_HITBMP,
				EPR_system_DataBaseContainer.T2_DAMAGE

		};
		
		t2_cursor = dbDriverA.query("t2_driverA", t2,
				null, null, null, null, null);
		
		t2_cursor.moveToFirst();
		if (t2_cursor != null && t2_cursor.moveToFirst()) {
			
			inputDrA_temp = new String[] {
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_ID)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_NAME)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_SURNAME)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_FATHERNAME)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_FULL)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_ADRESS)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_INDEX)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_COUNTRY)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_PHONE)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_EMAIL)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_MODEL)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_REGSIGN)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_REGCOUNTRY)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_SWITCH_PRICEP)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_REGSIGN_PRICEP)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_REGCOUNTRY_PRICEP)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_INSNAME)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_INSRADIO)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_SERIA)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_N)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_FROM)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_TO)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_DOCCOUNTRY)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_DOCTEL)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_SWITCH_INS)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_SURNAME_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_NAME_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_FATHERNAME_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_BIRTHDAY_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_ADRESS_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_INDEX_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_COUNTRY_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_PHONE_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_EMAIL_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_SERIES_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_NUM_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_CATEGORY_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_VALID_LIC)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_HITBMP)),
					t2_cursor.getString(t2_cursor.getColumnIndex(EPR_system_DataBaseContainer.T2_DAMAGE))
			};

			t2_cursor.close();
			t2_cursor.moveToFirst();
			
		}

		checkID.setText(inputDrA_temp[0], TextView.BufferType.EDITABLE);
		drA_01_Surname.setText(inputDrA_temp[1], TextView.BufferType.EDITABLE);
		drA_02_Name.setText(inputDrA_temp[2], TextView.BufferType.EDITABLE);
		drA_03_Fathername.setText(inputDrA_temp[3], TextView.BufferType.EDITABLE);
		drA_04_Full.setText(inputDrA_temp[4], TextView.BufferType.EDITABLE);
		drA_05_Adress.setText(inputDrA_temp[5], TextView.BufferType.EDITABLE);
		drA_06_Index.setText(inputDrA_temp[6], TextView.BufferType.EDITABLE);

		drA_08_Phone.setText(inputDrA_temp[8], TextView.BufferType.EDITABLE);
		drA_09_EMail.setText(inputDrA_temp[9], TextView.BufferType.EDITABLE);
		drA_10_Model.setText(inputDrA_temp[10], TextView.BufferType.EDITABLE);
		drA_11_RegSign.setText(inputDrA_temp[11], TextView.BufferType.EDITABLE);

		drA_s13_Switch_Pricep = inputDrA_temp[13];
		drA_14_RegSign_Pricep.setText(inputDrA_temp[14], TextView.BufferType.EDITABLE);

		drA_16_InsName.setText(inputDrA_temp[16], TextView.BufferType.EDITABLE);

		drA_21_Seria.setText(inputDrA_temp[18], TextView.BufferType.EDITABLE);
		drA_22_N.setText(inputDrA_temp[19], TextView.BufferType.EDITABLE);
		drA_23_From.setText(inputDrA_temp[20], TextView.BufferType.EDITABLE);
		drA_24_To.setText(inputDrA_temp[21], TextView.BufferType.EDITABLE);

		drA_26_DocTel.setText(inputDrA_temp[23], TextView.BufferType.EDITABLE);
		drA_s27_Switch_Ins = inputDrA_temp[24];
		drA_28_Surname_Lic.setText(inputDrA_temp[25], TextView.BufferType.EDITABLE);
		drA_29_Name_Lic.setText(inputDrA_temp[26], TextView.BufferType.EDITABLE);
		drA_30_FatherName_Lic.setText(inputDrA_temp[27], TextView.BufferType.EDITABLE);
		drA_31_BirthDay_Lic.setText(inputDrA_temp[28], TextView.BufferType.EDITABLE);
		drA_32_Adress_Lic.setText(inputDrA_temp[29], TextView.BufferType.EDITABLE);
		drA_33_Index_Lic.setText(inputDrA_temp[30], TextView.BufferType.EDITABLE);

		drA_35_Phone_Lic.setText(inputDrA_temp[32], TextView.BufferType.EDITABLE);
		drA_36_EMail_Lic.setText(inputDrA_temp[33], TextView.BufferType.EDITABLE);
		drA_37_Series_Lic.setText(inputDrA_temp[34], TextView.BufferType.EDITABLE);
		drA_38_Num_Lic.setText(inputDrA_temp[35], TextView.BufferType.EDITABLE);
		drA_39_Category_Lic.setText(inputDrA_temp[36], TextView.BufferType.EDITABLE);
		drA_40_Valid_Lic.setText(inputDrA_temp[37], TextView.BufferType.EDITABLE);

		drA_42_damage.setText(inputDrA_temp[39], TextView.BufferType.EDITABLE);

		drA_41_HitBMP.setImageDrawable(Drawable.createFromPath(drA_s41_HitBMP));

		String[] countries_array = mContext.getResources().getStringArray(R.array.countries_array_full);

		for (int i=0; i < countries_array.length; i++) {
			if (countries_array[i] == drA_s07_Country) {
				drA_07_Country.setSelection(i);
			}
			if (countries_array[i] == drA_s12_RegCountry) {
				drA_12_RegCountry.setSelection(i);
			}
			if (countries_array[i] == drA_s15_RegCountry_Pricep) {
				drA_15_RegCountry_Pricep.setSelection(i);
			}
			if (countries_array[i] == drA_s25_DocCountry) {
				drA_25_DocCountry.setSelection(i);
			}
			if (countries_array[i] == drA_s34_Country_Lic) {
				drA_34_Country_Lic.setSelection(i);
			}
		}

		String[] polis_array = {
				drA_s18_IR1,
				drA_s19_IR2,
				drA_s20_IR3
		};

		RadioButton[] radio_array = {
				drA_18_IR1,
				drA_19_IR2,
				drA_20_IR3
		};

		for (int i=0; i < polis_array.length; i++) {
			if (drA_s17_InsRadio == polis_array[i]) {
				radio_array[i].setChecked(true);
			}
		}

		boolean[] checkBool = new boolean[2];
		
		checkBool[0] = inputDrA_temp[13] == yes;
		checkBool[1] = inputDrA_temp[24] == yes;

		
		if (checkBool[0]) {
			drA_13_Switch_Pricep.setChecked(checkBool[0]);
		}
		if (checkBool[1]) {
			drA_27_Switch_Ins.setChecked(checkBool[1]);
		}

	}
	
	/** Конец Вставка сохранённых значений **/

	/** Обработка базы данных */

	public void getStrings () {

		valuesDrA_temp = new String[]{
				checkID.getText().toString(),
				drA_01_Surname.getText().toString(),
				drA_02_Name.getText().toString(),
				drA_03_Fathername.getText().toString(),
				drA_04_Full.getText().toString(),
				drA_05_Adress.getText().toString(),
				drA_06_Index.getText().toString(),
				drA_s07_Country,
				drA_08_Phone.getText().toString(),
				drA_09_EMail.getText().toString(),
				drA_10_Model.getText().toString(),
				drA_11_RegSign.getText().toString(),
				drA_s12_RegCountry,
				drA_13_Switch_Pricep.getText().toString(),
				drA_14_RegSign_Pricep.getText().toString(),
				drA_s15_RegCountry_Pricep,
				drA_16_InsName.getText().toString(),
				drA_s17_InsRadio,
				drA_21_Seria.getText().toString(),
				drA_22_N.getText().toString(),
				drA_23_From.getText().toString(),
				drA_24_To.getText().toString(),
				drA_s25_DocCountry,
				drA_26_DocTel.getText().toString(),
				drA_27_Switch_Ins.getText().toString(),
				drA_28_Surname_Lic.getText().toString(),
				drA_29_Name_Lic.getText().toString(),
				drA_30_FatherName_Lic.getText().toString(),
				drA_31_BirthDay_Lic.getText().toString(),
				drA_32_Adress_Lic.getText().toString(),
				drA_33_Index_Lic.getText().toString(),
				drA_s34_Country_Lic,
				drA_35_Phone_Lic.getText().toString(),
				drA_36_EMail_Lic.getText().toString(),
				drA_37_Series_Lic.getText().toString(),
				drA_38_Num_Lic.getText().toString(),
				drA_39_Category_Lic.getText().toString(),
				drA_40_Valid_Lic.getText().toString(),
				drA_s41_HitBMP,
				drA_42_damage.getText().toString()
		};

		for (int i=0; i < valuesDrA_temp.length; i++) {
			if (valuesDrA_temp[i] == null) {
				valuesDrA_temp[i] = "";
			}
		}

		drA_s00_IDA = valuesDrA_temp[0];
		drA_s01_Surname = valuesDrA_temp[1];
		drA_s02_Name = valuesDrA_temp[2];
		drA_s03_Fathername = valuesDrA_temp[3];
		drA_s04_Full = valuesDrA_temp[4];
		drA_s05_Adress = valuesDrA_temp[5];
		drA_s06_Index = valuesDrA_temp[6];
		drA_s07_Country = valuesDrA_temp[7];
		drA_s08_Phone = valuesDrA_temp[8];
		drA_s09_EMail = valuesDrA_temp[9];
		drA_s10_Model = valuesDrA_temp[10];
		drA_s11_RegSign = valuesDrA_temp[11];
		drA_s12_RegCountry = valuesDrA_temp[12];
		drA_s13_Switch_Pricep = valuesDrA_temp[13];
		drA_s14_RegSign_Pricep = valuesDrA_temp[14];
		drA_s15_RegCountry_Pricep = valuesDrA_temp[15];
		drA_s16_InsName = valuesDrA_temp[16];
		drA_s17_InsRadio = valuesDrA_temp[17];
		drA_s21_Seria = valuesDrA_temp[18];
		drA_s22_N = valuesDrA_temp[19];
		drA_s23_From = valuesDrA_temp[20];
		drA_s24_To = valuesDrA_temp[21];
		drA_s25_DocCountry = valuesDrA_temp[22];
		drA_s26_DocTel = valuesDrA_temp[23];
		drA_s27_Switch_Ins = valuesDrA_temp[24];
		drA_s28_Surname_Lic = valuesDrA_temp[25];
		drA_s29_Name_Lic = valuesDrA_temp[26];
		drA_s30_FatherName_Lic = valuesDrA_temp[27];
		drA_s31_BirthDay_Lic = valuesDrA_temp[28];
		drA_s32_Adress_Lic = valuesDrA_temp[29];
		drA_s33_Index_Lic = valuesDrA_temp[30];
		drA_s34_Country_Lic = valuesDrA_temp[31];
		drA_s35_Phone_Lic = valuesDrA_temp[32];
		drA_s36_EMail_Lic = valuesDrA_temp[33];
		drA_s37_Series_Lic = valuesDrA_temp[34];
		drA_s38_Num_Lic = valuesDrA_temp[35];
		drA_s39_Category_Lic = valuesDrA_temp[36];
		drA_s40_Valid_Lic = valuesDrA_temp[37];
		drA_s41_HitBMP = valuesDrA_temp[38];
		drA_s42_damage = valuesDrA_temp[39];

	}

	public void getBase () {

		getStrings();

		ContentValues t2_values = new ContentValues();

		// Значения столбцов
		t2_values.put(EPR_system_DataBaseContainer.T2_ID, drA_s00_IDA);
		t2_values.put(EPR_system_DataBaseContainer.T2_SURNAME, drA_s01_Surname);
		t2_values.put(EPR_system_DataBaseContainer.T2_NAME, drA_s02_Name);
		t2_values.put(EPR_system_DataBaseContainer.T2_FATHERNAME, drA_s03_Fathername);
		t2_values.put(EPR_system_DataBaseContainer.T2_FULL, drA_s04_Full);
		t2_values.put(EPR_system_DataBaseContainer.T2_ADRESS, drA_s05_Adress);
		t2_values.put(EPR_system_DataBaseContainer.T2_INDEX, drA_s06_Index);
		t2_values.put(EPR_system_DataBaseContainer.T2_COUNTRY, drA_s07_Country);
		t2_values.put(EPR_system_DataBaseContainer.T2_PHONE, drA_s08_Phone);
		t2_values.put(EPR_system_DataBaseContainer.T2_EMAIL, drA_s09_EMail);
		t2_values.put(EPR_system_DataBaseContainer.T2_MODEL, drA_s10_Model);
		t2_values.put(EPR_system_DataBaseContainer.T2_REGSIGN, drA_s11_RegSign);
		t2_values.put(EPR_system_DataBaseContainer.T2_REGCOUNTRY, drA_s12_RegCountry);
		t2_values.put(EPR_system_DataBaseContainer.T2_SWITCH_PRICEP, drA_s13_Switch_Pricep);
		t2_values.put(EPR_system_DataBaseContainer.T2_REGSIGN_PRICEP, drA_s14_RegSign_Pricep);
		t2_values.put(EPR_system_DataBaseContainer.T2_REGCOUNTRY_PRICEP, drA_s15_RegCountry_Pricep);
		t2_values.put(EPR_system_DataBaseContainer.T2_INSNAME, drA_s16_InsName);
		t2_values.put(EPR_system_DataBaseContainer.T2_INSRADIO, drA_s17_InsRadio);
		t2_values.put(EPR_system_DataBaseContainer.T2_SERIA, drA_s21_Seria);
		t2_values.put(EPR_system_DataBaseContainer.T2_N, drA_s22_N);
		t2_values.put(EPR_system_DataBaseContainer.T2_FROM, drA_s23_From);
		t2_values.put(EPR_system_DataBaseContainer.T2_TO, drA_s24_To);
		t2_values.put(EPR_system_DataBaseContainer.T2_DOCCOUNTRY, drA_s25_DocCountry);
		t2_values.put(EPR_system_DataBaseContainer.T2_DOCTEL, drA_s26_DocTel);
		t2_values.put(EPR_system_DataBaseContainer.T2_SWITCH_INS, drA_s27_Switch_Ins);
		t2_values.put(EPR_system_DataBaseContainer.T2_SURNAME_LIC, drA_s28_Surname_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_NAME_LIC, drA_s29_Name_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_FATHERNAME_LIC, drA_s30_FatherName_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_BIRTHDAY_LIC, drA_s31_BirthDay_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_ADRESS_LIC, drA_s32_Adress_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_INDEX_LIC, drA_s33_Index_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_COUNTRY_LIC, drA_s34_Country_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_PHONE_LIC, drA_s35_Phone_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_EMAIL_LIC, drA_s36_EMail_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_SERIES_LIC, drA_s37_Series_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_NUM_LIC, drA_s38_Num_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_CATEGORY_LIC, drA_s39_Category_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_VALID_LIC, drA_s40_Valid_Lic);
		t2_values.put(EPR_system_DataBaseContainer.T2_HITBMP, drA_s41_HitBMP);
		t2_values.put(EPR_system_DataBaseContainer.T2_DAMAGE, drA_s42_damage);

				// Вставляем данные в таблицу

		if (dataCheckFile.exists() && dataCheckAll.exists()) {
			dbDriverA.update(
					"t2_driverA",
					t2_values,
					EPR_system_DataBaseContainer.T2_ID + "= ? OR " +
							EPR_system_DataBaseContainer.T2_SURNAME + "= ? OR " +
							EPR_system_DataBaseContainer.T2_NAME + "= ? OR " +
							EPR_system_DataBaseContainer.T2_FATHERNAME + "= ? OR " +
							EPR_system_DataBaseContainer.T2_FULL + "= ? OR " +
							EPR_system_DataBaseContainer.T2_ADRESS + "= ? OR " +
							EPR_system_DataBaseContainer.T2_INDEX + "= ? OR " +
							EPR_system_DataBaseContainer.T2_COUNTRY + "= ? OR " +
							EPR_system_DataBaseContainer.T2_PHONE + "= ? OR " +
							EPR_system_DataBaseContainer.T2_EMAIL + "= ? OR " +
							EPR_system_DataBaseContainer.T2_MODEL + "= ? OR " +
							EPR_system_DataBaseContainer.T2_REGSIGN + "= ? OR " +
							EPR_system_DataBaseContainer.T2_REGCOUNTRY + "= ? OR " +
							EPR_system_DataBaseContainer.T2_SWITCH_PRICEP + "= ? OR " +
							EPR_system_DataBaseContainer.T2_REGSIGN_PRICEP + "= ? OR " +
							EPR_system_DataBaseContainer.T2_REGCOUNTRY_PRICEP + "= ? OR " +
							EPR_system_DataBaseContainer.T2_INSNAME + "= ? OR " +
							EPR_system_DataBaseContainer.T2_INSRADIO + "= ? OR " +
							EPR_system_DataBaseContainer.T2_SERIA + "= ? OR " +
							EPR_system_DataBaseContainer.T2_N + "= ? OR " +
							EPR_system_DataBaseContainer.T2_FROM + "= ? OR " +
							EPR_system_DataBaseContainer.T2_TO + "= ? OR " +
							EPR_system_DataBaseContainer.T2_DOCCOUNTRY + "= ? OR " +
							EPR_system_DataBaseContainer.T2_DOCTEL + "= ? OR " +
							EPR_system_DataBaseContainer.T2_SWITCH_INS + "= ? OR " +
							EPR_system_DataBaseContainer.T2_SURNAME_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_NAME_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_FATHERNAME_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_BIRTHDAY_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_ADRESS_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_INDEX_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_COUNTRY_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_PHONE_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_EMAIL_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_SERIES_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_NUM_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_CATEGORY_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_VALID_LIC + "= ? OR " +
							EPR_system_DataBaseContainer.T2_HITBMP + "= ? OR " +
							EPR_system_DataBaseContainer.T2_DAMAGE + "= ?",
					new String[]{
							drA_s00_IDA,
							drA_s01_Surname,
							drA_s02_Name,
							drA_s03_Fathername,
							drA_s04_Full,
							drA_s05_Adress,
							drA_s06_Index,
							drA_s07_Country,
							drA_s08_Phone,
							drA_s09_EMail,
							drA_s10_Model,
							drA_s11_RegSign,
							drA_s12_RegCountry,
							drA_s13_Switch_Pricep,
							drA_s14_RegSign_Pricep,
							drA_s15_RegCountry_Pricep,
							drA_s16_InsName,
							drA_s17_InsRadio,
							drA_s21_Seria,
							drA_s22_N,
							drA_s23_From,
							drA_s24_To,
							drA_s25_DocCountry,
							drA_s26_DocTel,
							drA_s27_Switch_Ins,
							drA_s28_Surname_Lic,
							drA_s29_Name_Lic,
							drA_s30_FatherName_Lic,
							drA_s31_BirthDay_Lic,
							drA_s32_Adress_Lic,
							drA_s33_Index_Lic,
							drA_s34_Country_Lic,
							drA_s35_Phone_Lic,
							drA_s36_EMail_Lic,
							drA_s37_Series_Lic,
							drA_s38_Num_Lic,
							drA_s39_Category_Lic,
							drA_s40_Valid_Lic,
							drA_s41_HitBMP,
							drA_s42_damage
					}
			);
		} else {
			dbDriverA.insert(
					"t2_driverA",
					null,
					t2_values
			);
			checkFileCreate();
		}

		assert previewDriverA != null;
		db_driverA =
				"ID   " + drA_s00_IDA
				+ "\nФамилия   " + drA_s01_Surname
				+ "\nИмя  " + drA_s02_Name
				+ "\nОтчество   " + drA_s03_Fathername
				+ "\nНаим. юр. лица   " + drA_s04_Full
				+ "\nАдрес   " + drA_s05_Adress
				+ "\nИндекс   " + drA_s06_Index
				+ "\nСтрана   " + drA_s07_Country
				+ "\nТел.   " + drA_s08_Phone
				+ "\ne-mail   " + drA_s09_EMail
				+ "\nМарка, модель   " + drA_s10_Model
				+ "\nРег. знак   " + drA_s11_RegSign
				+ "\nСтрана регистрации   " + drA_s12_RegCountry
				+ "\nПрицеп   " + drA_s13_Switch_Pricep
				+ "\nРег. знак прицепа   " + drA_s14_RegSign_Pricep
				+ "\nСтрана регистрации прицепа   " + drA_s15_RegCountry_Pricep
				+ "\nСтраховая компания   " + drA_s16_InsName
				+ "\nНаим. документа   " + drA_s17_InsRadio
				+ "\nстраховое свидетельство   " + drA_s18_IR1
				+ "\nполис   " + drA_s19_IR2
				+ "\nсертификат   " + drA_s20_IR3
				+ "\nСерия   " + drA_s21_Seria
				+ "\n№   " + drA_s22_N
				+ "\nДействительно с   " + drA_s23_From
				+ "\nпо   " + drA_s24_To
				+ "\nСтрана   " + drA_s25_DocCountry
				+ "\nТел. или e-mail   " + drA_s26_DocTel
				+ "\nЗастраховано?   " + drA_s27_Switch_Ins
				+ "\nФамилия   " + drA_s28_Surname_Lic
				+ "\nИмя   " + drA_s29_Name_Lic
				+ "\nОтчество   " + drA_s30_FatherName_Lic
				+ "\nДата рождения   " + drA_s31_BirthDay_Lic
				+ "\nАдрес   " + drA_s32_Adress_Lic
				+ "\nИндекс   " + drA_s33_Index_Lic
				+ "\nСтрана   " + drA_s34_Country_Lic
				+ "\nТел.   " + drA_s35_Phone_Lic
				+ "\ne-mail   " + drA_s36_EMail_Lic
				+ "\nСерия   " + drA_s37_Series_Lic
				+ "\n№   " + drA_s38_Num_Lic
				+ "\nКатегория   " + drA_s39_Category_Lic
				+ "\nДействительно до   " + drA_s40_Valid_Lic
				+ "\nВидимые поврежд.   " + drA_s42_damage
				+ "\nМесто удара   " + drA_s41_HitBMP;

	}

	public void checkFileCreate () {

		String sdState = android.os.Environment.getExternalStorageState();
		if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			dataCheckFile = new File(Path_sys, dataCheckPath + dataCheckNameDrA);
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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_driver_a);
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

        mDrawerIns = (DrawerLayout) findViewById(R.id.drawer_layout_driver_a_ins);
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

        InsChooseA = (TextView) findViewById(R.id.driver_a_insurance_name);
        InsChooseA.setText(Chosen);
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
				previewDriverA = (TextView)findViewById(R.id.testBase);
				previewDriverA.setText(db_driverA, TextView.BufferType.EDITABLE);
				previewDriverA.setVisibility((previewDriverA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
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

				ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");

				File dbSelf = new File("/data/data/com.mainfish.europrotocola04/databases/am_protocol.db");
				dbSelf.delete();

				dataCheckFile.delete();
				dataCheckAll.delete();

				File oldBaseDrGen = new File(Path, "/db_gen.exist");
				oldBaseDrGen.delete();

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

		previewDriverA.setVisibility((previewDriverA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewImg.setVisibility((previewImg.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewRow1.setVisibility((previewRow1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
		previewRow2.setVisibility((previewRow2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

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

    public void btnOpenIns (View view) {

        if(mSlideStateIns){
            mDrawerIns.closeDrawer(GravityCompat.START);
        }else{
            mDrawerIns.openDrawer(GravityCompat.START);
        }

    }

    public void accident_generalClick (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    getBase();

        Intent intentGeneral = new Intent(this, EPR_p02_General.class);
        startActivity(intentGeneral);

    }

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
			mDrawerLayout.closeDrawer(GravityCompat.END);
		} else if (mDrawerIns.isDrawerOpen(GravityCompat.START)) {
			mDrawerIns.closeDrawer(GravityCompat.START);
		} else {

			ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");

			getBase();
			super.onBackPressed();
		}
	}

    /** Disabled **/



    public void drawHitA (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    getBase();

        Intent intentDrawHitA = new Intent(this, xEPR_DrawHitActivity.class);
        startActivity(intentDrawHitA);

    }

    public void drawScheme (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    getBase();

        Intent intentDrawScheme = new Intent(this, xEPR_SchemeDTP.class);
        startActivity(intentDrawScheme);

    }

    public void drawSign (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    getBase();

        Intent intentDrawSign = new Intent(this, xEPR_Signature.class);
        startActivity(intentDrawSign);

    }
}