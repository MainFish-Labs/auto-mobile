package com.mainfish.europrotocola04;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/** Created by MainFish on 08/07/16.  */

public class EPR_system_DataBaseContainer extends SQLiteOpenHelper implements BaseColumns {

    // Имя базы данных
    private static final String DATABASE_NAME = "am_protocol.db";
    private static final String TABLE_NAME = "t1_general";
    private static final String TABLE_NAME_DRA = "t2_driverA";
    private static final String TABLE_NAME_DRB = "t3_driverB";
    private static final String TABLE_NAME_CIRCUMA = "t4_circumA";
    private static final String TABLE_NAME_CIRCUMB = "t4_circumB";
    private static final String TABLE_NAME_SCHEME = "t5_scheme";
    private static final String TABLE_NAME_SIGNA = "t6a_sign";
    private static final String TABLE_NAME_SIGNB = "t6b_sign";

    // Версия базы данных
    private static final int DATABASE_VERSION = 1;

    // Названия столбцов
    public static final String T1_DATE = "t1_date";
    public static final String T1_TIME = "t1_time";
    public static final String T1_COUNTRY = "t1_country";
    public static final String T1_GEO = "t1_geo";
    public static final String T1_M_PLACE = "t1_m_place";
    public static final String T1_Q1="t1_q1";
    public static final String T1_Q2="t1_q2";
    public static final String T1_Q3="t1_q3";
    public static final String T1_W1="t1_w1";
    public static final String T1_W2="t1_w2";
    public static final String T1_W3="t1_w3";
    public static final String T1_W4="t1_w4";

    public static final String T2_ID = "t2_id";
    public static final String T2_SURNAME = "t2_surname";
    public static final String T2_NAME = "t2_name";
    public static final String T2_FATHERNAME = "t2_fathername";
    public static final String T2_FULL = "t2_full";
    public static final String T2_ADRESS = "t2_adress";
    public static final String T2_INDEX = "t2_index";
    public static final String T2_COUNTRY = "t2_country";
    public static final String T2_PHONE = "t2_phone";
    public static final String T2_EMAIL = "t2_email";
    public static final String T2_MODEL = "t2_model";
    public static final String T2_REGSIGN = "t2_regsign";
    public static final String T2_REGCOUNTRY = "t2_regcountry";
    public static final String T2_SWITCH_PRICEP = "t2_switch_pricep";
    public static final String T2_REGSIGN_PRICEP = "t2_regsign_pricep";
    public static final String T2_REGCOUNTRY_PRICEP = "t2_regcountry_pricep";
    public static final String T2_INSNAME = "t2_insname";
    public static final String T2_INSRADIO = "t2_insradio";
    public static final String T2_SERIA = "t2_seria";
    public static final String T2_N = "t2_n";
    public static final String T2_FROM = "t2_from";
    public static final String T2_TO = "t2_to";
    public static final String T2_DOCCOUNTRY = "t2_doccountry";
    public static final String T2_DOCTEL = "t2_doctel";
    public static final String T2_SWITCH_INS = "t2_switch_ins";
    public static final String T2_SURNAME_LIC = "t2_surname_lic";
    public static final String T2_NAME_LIC = "t2_name_lic";
    public static final String T2_FATHERNAME_LIC = "t2_fathername_lic";
    public static final String T2_BIRTHDAY_LIC = "t2_birthday_lic";
    public static final String T2_ADRESS_LIC = "t2_adress_lic";
    public static final String T2_INDEX_LIC = "t2_index_lic";
    public static final String T2_COUNTRY_LIC = "t2_country_lic";
    public static final String T2_PHONE_LIC = "t2_phone_lic";
    public static final String T2_EMAIL_LIC = "t2_email_lic";
    public static final String T2_SERIES_LIC = "t2_series_lic";
    public static final String T2_NUM_LIC = "t2_num_lic";
    public static final String T2_CATEGORY_LIC = "t2_category_lic";
    public static final String T2_VALID_LIC = "t2_valid_lic";
    public static final String T2_HITBMP = "t2_hitbmp";
    public static final String T2_DAMAGE = "t2_damage";

    public static final String T3_ID = "t3_id";
    public static final String T3_SURNAME = "t3_surname";
    public static final String T3_NAME = "t3_name";
    public static final String T3_FATHERNAME = "t3_fathername";
    public static final String T3_FULL = "t3_full";
    public static final String T3_ADRESS = "t3_adress";
    public static final String T3_INDEX = "t3_index";
    public static final String T3_COUNTRY = "t3_country";
    public static final String T3_PHONE = "t3_phone";
    public static final String T3_EMAIL = "t3_email";
    public static final String T3_MODEL = "t3_model";
    public static final String T3_REGSIGN = "t3_regsign";
    public static final String T3_REGCOUNTRY = "t3_regcountry";
    public static final String T3_SWITCH_PRICEP = "t3_switch_pricep";
    public static final String T3_REGSIGN_PRICEP = "t3_regsign_pricep";
    public static final String T3_REGCOUNTRY_PRICEP = "t3_regcountry_pricep";
    public static final String T3_INSNAME = "t3_insname";
    public static final String T3_INSRADIO = "t3_insradio";
    public static final String T3_SERIA = "t3_seria";
    public static final String T3_N = "t3_n";
    public static final String T3_FROM = "t3_from";
    public static final String T3_TO = "t3_to";
    public static final String T3_DOCCOUNTRY = "t3_doccountry";
    public static final String T3_DOCTEL = "t3_doctel";
    public static final String T3_SWITCH_INS = "t3_switch_ins";
    public static final String T3_SURNAME_LIC = "t3_surname_lic";
    public static final String T3_NAME_LIC = "t3_name_lic";
    public static final String T3_FATHERNAME_LIC = "t3_fathername_lic";
    public static final String T3_BIRTHDAY_LIC = "t3_birthday_lic";
    public static final String T3_ADRESS_LIC = "t3_adress_lic";
    public static final String T3_INDEX_LIC = "t3_index_lic";
    public static final String T3_COUNTRY_LIC = "t3_country_lic";
    public static final String T3_PHONE_LIC = "t3_phone_lic";
    public static final String T3_EMAIL_LIC = "t3_email_lic";
    public static final String T3_SERIES_LIC = "t3_series_lic";
    public static final String T3_NUM_LIC = "t3_num_lic";
    public static final String T3_CATEGORY_LIC = "t3_category_lic";
    public static final String T3_VALID_LIC = "t3_valid_lic";
    public static final String T3_HITBMP = "t3_hitbmp";
    public static final String T3_DAMAGE = "t3_damage";
    
    public static final String T4A_01 = "t4a_01";
    public static final String T4A_02 = "t4a_02";
    public static final String T4A_03 = "t4a_03";
    public static final String T4A_04 = "t4a_04";
    public static final String T4A_05 = "t4a_05";
    public static final String T4A_06 = "t4a_06";
    public static final String T4A_07 = "t4a_07";
    public static final String T4A_08 = "t4a_08";
    public static final String T4A_09 = "t4a_09";
    public static final String T4A_10 = "t4a_10";
    public static final String T4A_11 = "t4a_11";
    public static final String T4A_12 = "t4a_12";
    public static final String T4A_13 = "t4a_13";
    public static final String T4A_14 = "t4a_14";
    public static final String T4A_15 = "t4a_15";
    public static final String T4A_16 = "t4a_16";
    public static final String T4A_17 = "t4a_17";
    public static final String T4A_18 = "t4a_18";
    
    public static final String T4B_01 = "t4b_01";
    public static final String T4B_02 = "t4b_02";
    public static final String T4B_03 = "t4b_03";
    public static final String T4B_04 = "t4b_04";
    public static final String T4B_05 = "t4b_05";
    public static final String T4B_06 = "t4b_06";
    public static final String T4B_07 = "t4b_07";
    public static final String T4B_08 = "t4b_08";
    public static final String T4B_09 = "t4b_09";
    public static final String T4B_10 = "t4b_10";
    public static final String T4B_11 = "t4b_11";
    public static final String T4B_12 = "t4b_12";
    public static final String T4B_13 = "t4b_13";
    public static final String T4B_14 = "t4b_14";
    public static final String T4B_15 = "t4b_15";
    public static final String T4B_16 = "t4b_16";
    public static final String T4B_17 = "t4b_17";
    public static final String T4B_18 = "t4b_18";

	public static final String T5_01 = "t5_01";

	public static final String T6A_PRIM = "t6a_prim";
	public static final String T6A_SIGN = "t6a_sign";
	public static final String T6A_NAME = "t6a_name";
	public static final String T6A_SIGN_RESP = "t6a_sign_resp";
	public static final String T6A_NAME_RESP = "t6a_name_resp";

	public static final String T6B_PRIM = "t6b_prim";
	public static final String T6B_SIGN = "t6b_sign";
	public static final String T6B_NAME = "t6b_name";
	public static final String T6B_SIGN_RESP = "t6b_sign_resp";
	public static final String T6B_NAME_RESP = "t6b_name_resp";

    // Создание таблицы
    private static final String DATABASE_CREATE_SCRIPT_T1 = "create table " + TABLE_NAME + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + T1_DATE + " text, "
            + T1_TIME + " text, "
            + T1_COUNTRY + " text, "
            + T1_GEO + " text, "
            + T1_M_PLACE + " text, "
            + T1_Q1 +   " text, "
            + T1_Q2 + " text, "
            + T1_Q3 + " text, "
            + T1_W1 + " text, "
            + T1_W2 + " text, "
            + T1_W3 + " text, "
            + T1_W4 + " text);";

    private static final String DATABASE_CREATE_SCRIPT_T2 = "create table " + TABLE_NAME_DRA + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + T2_ID + " text, "
            + T2_SURNAME + " text, "
            + T2_NAME + " text, "
            + T2_FATHERNAME + " text, "
            + T2_FULL + " text, "
            + T2_ADRESS + " text, "
            + T2_INDEX + " text, "
            + T2_COUNTRY + " text, "
            + T2_PHONE + " text, "
            + T2_EMAIL + " text, "
            + T2_MODEL + " text, "
            + T2_REGSIGN + " text, "
            + T2_REGCOUNTRY + " text, "
            + T2_SWITCH_PRICEP + " text, "
            + T2_REGSIGN_PRICEP + " text, "
            + T2_REGCOUNTRY_PRICEP + " text, "
            + T2_INSNAME + " text, "
            + T2_INSRADIO + " text, "
            + T2_SERIA + " text, "
            + T2_N + " text, "
            + T2_FROM + " text, "
            + T2_TO + " text, "
            + T2_DOCCOUNTRY + " text, "
            + T2_DOCTEL + " text, "
            + T2_SWITCH_INS + " text, "
            + T2_SURNAME_LIC + " text, "
            + T2_NAME_LIC + " text, "
            + T2_FATHERNAME_LIC + " text, "
            + T2_BIRTHDAY_LIC + " text, "
            + T2_ADRESS_LIC + " text, "
            + T2_INDEX_LIC + " text, "
            + T2_COUNTRY_LIC + " text, "
            + T2_PHONE_LIC + " text, "
            + T2_EMAIL_LIC + " text, "
            + T2_SERIES_LIC + " text, "
            + T2_NUM_LIC + " text, "
            + T2_CATEGORY_LIC + " text, "
            + T2_VALID_LIC + " text, "
            + T2_HITBMP + " text, "
            + T2_DAMAGE + " text);";

    private static final String DATABASE_CREATE_SCRIPT_T3 = "create table " + TABLE_NAME_DRB + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + T3_ID + " text, "
            + T3_SURNAME + " text, "
            + T3_NAME + " text, "
            + T3_FATHERNAME + " text, "
            + T3_FULL + " text, "
            + T3_ADRESS + " text, "
            + T3_INDEX + " text, "
            + T3_COUNTRY + " text, "
            + T3_PHONE + " text, "
            + T3_EMAIL + " text, "
            + T3_MODEL + " text, "
            + T3_REGSIGN + " text, "
            + T3_REGCOUNTRY + " text, "
            + T3_SWITCH_PRICEP + " text, "
            + T3_REGSIGN_PRICEP + " text, "
            + T3_REGCOUNTRY_PRICEP + " text, "
            + T3_INSNAME + " text, "
            + T3_INSRADIO + " text, "
            + T3_SERIA + " text, "
            + T3_N + " text, "
            + T3_FROM + " text, "
            + T3_TO + " text, "
            + T3_DOCCOUNTRY + " text, "
            + T3_DOCTEL + " text, "
            + T3_SWITCH_INS + " text, "
            + T3_SURNAME_LIC + " text, "
            + T3_NAME_LIC + " text, "
            + T3_FATHERNAME_LIC + " text, "
            + T3_BIRTHDAY_LIC + " text, "
            + T3_ADRESS_LIC + " text, "
            + T3_INDEX_LIC + " text, "
            + T3_COUNTRY_LIC + " text, "
            + T3_PHONE_LIC + " text, "
            + T3_EMAIL_LIC + " text, "
            + T3_SERIES_LIC + " text, "
            + T3_NUM_LIC + " text, "
            + T3_CATEGORY_LIC + " text, "
            + T3_VALID_LIC + " text, "
            + T3_HITBMP + " text, "
            + T3_DAMAGE + " text);";
    
    private static final String DATABASE_CREATE_SCRIPT_T4A = "create table " + TABLE_NAME_CIRCUMA + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + T4A_01 + " text, "
            + T4A_02 + " text, "
            + T4A_03 + " text, "
            + T4A_04 + " text, "
            + T4A_05 + " text, "
            + T4A_06 + " text, "
            + T4A_07 + " text, "
            + T4A_08 + " text, "
            + T4A_09 + " text, "
            + T4A_10 + " text, "
            + T4A_11 + " text, "
            + T4A_12 + " text, "
            + T4A_13 + " text, "
            + T4A_14 + " text, "
            + T4A_15 + " text, "
            + T4A_16 + " text, "
            + T4A_17 + " text, "
            + T4A_18 + " text);";
    
    private static final String DATABASE_CREATE_SCRIPT_T4B = "create table " + TABLE_NAME_CIRCUMB + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + T4B_01 + " text, "
            + T4B_02 + " text, "
            + T4B_03 + " text, "
            + T4B_04 + " text, "
            + T4B_05 + " text, "
            + T4B_06 + " text, "
            + T4B_07 + " text, "
            + T4B_08 + " text, "
            + T4B_09 + " text, "
            + T4B_10 + " text, "
            + T4B_11 + " text, "
            + T4B_12 + " text, "
            + T4B_13 + " text, "
            + T4B_14 + " text, "
            + T4B_15 + " text, "
            + T4B_16 + " text, "
            + T4B_17 + " text, "
            + T4B_18 + " text);";

	private static final String DATABASE_CREATE_SCRIPT_T5 = "create table " + TABLE_NAME_SCHEME + " ("
			+ BaseColumns._ID + " integer primary key autoincrement, "
			+ T5_01 + " text);";

	private static final String DATABASE_CREATE_SCRIPT_T6A = "create table " + TABLE_NAME_SIGNA + " ("
			+ BaseColumns._ID + " integer primary key autoincrement, "
			+ T6A_PRIM + " text, "
			+ T6A_SIGN + " text, "
			+ T6A_NAME + " text, "
			+ T6A_SIGN_RESP + " text, "
			+ T6A_NAME_RESP + " text);";

	private static final String DATABASE_CREATE_SCRIPT_T6B = "create table " + TABLE_NAME_SIGNB + " ("
			+ BaseColumns._ID + " integer primary key autoincrement, "
			+ T6B_PRIM + " text, "
			+ T6B_SIGN + " text, "
			+ T6B_NAME + " text, "
			+ T6B_SIGN_RESP + " text, "
			+ T6B_NAME_RESP + " text);";

    EPR_system_DataBaseContainer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public EPR_system_DataBaseContainer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_SCRIPT_T1);
        db.execSQL(DATABASE_CREATE_SCRIPT_T2);
        db.execSQL(DATABASE_CREATE_SCRIPT_T3);
        db.execSQL(DATABASE_CREATE_SCRIPT_T4A);
        db.execSQL(DATABASE_CREATE_SCRIPT_T4B);
        db.execSQL(DATABASE_CREATE_SCRIPT_T5);
        db.execSQL(DATABASE_CREATE_SCRIPT_T6A);
        db.execSQL(DATABASE_CREATE_SCRIPT_T6B);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Update table from " + oldVersion + " to " + newVersion);

        // Удаляем старую таблицу
        db.execSQL("DROP TABLE IF IT EXISTS " + TABLE_NAME);
        // Создаём новую
        onCreate(db);

    }
}
