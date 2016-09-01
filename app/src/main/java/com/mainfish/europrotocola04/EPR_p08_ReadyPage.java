package com.mainfish.europrotocola04;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by artli on 12.05.2016.
 */
public class EPR_p08_ReadyPage extends AppCompatActivity {

    public TextView SaveTxt;
    public TextView SendTxt;

    public String path_path, file_name;

    /** Переменные полей ввода */

    public ImageView SchemeDTP;

    /** Переменные базы данных */

    public Cursor t1_cursor, t2_cursor, t3_cursor, t4A_cursor, t4B_cursor, t5_cursor, t6a_cursor, t6b_cursor; // !!!!

    public int total_gen = 12;
    public int total_drA = 40;
    public int total_drB = 40;
    public int total_circum = 18;
    public int total_sign = 5;

    public String yes = "Да", no = "Нет", empty = "";
    public String textTSA = "\nВодитель ТС A - ", textTSB = "\nВодитель ТС B - ";

    private EPR_system_DataBaseContainer mDataBase;
    private SQLiteDatabase dbReady;
    private String db_general, db_driverA, db_driverB, db_circum, db_Scheme, previewPrimA, previewPrimB, previewSignA, previewSignB,previewNameA, previewNameB, previewSignA_resp, previewSignB_resp, previewNameA_resp, previewNameB_resp;
    private TextView previewGeneral, previewDriverA, previewDriverB, previewCircum, previewScheme, preview_primA, preview_nameA, preview_nameA_resp, preview_primB, preview_nameB, preview_nameB_resp, closePreview;
    private ScrollView previewScroll;
    private ImageView previewImg, previewImgSignA, previewImgSignB, previewImgSignA_resp, previewImgSignB_resp, previewImgDrA, previewImgDrB, previewImg_scheme;
    private TableLayout previewTable;
    private TableRow previewRow1, previewRow2, previewRow1a, previewRow1b, previewRow2a, previewRow2b, previewRow3a, previewRow3b, previewRow4a, previewRow4b, previewRow5a, previewRow5b, previewRowSpace, previewRow_gen, testBaseRow_drA1, testBaseRow_drA2, testBaseRow_drB1, testBaseRow_drB2, testBaseRow_circum, testBaseRow_scheme1, testBaseRow_scheme2;

    public String gen_date, gen_time, gen_country, gen_geo, gen_city;
    public String q_text1, q_text2, q_text3;
    public String wit_text1, wit_text2, wit_text3, wit_text4;

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

    public String schemeImage;

    public String primA, primB, nameA, nameB, nameA_resp, nameB_resp, signA, signB, signA_resp, signB_resp;

    public File Path_sys = android.os.Environment.getExternalStorageDirectory();
    public File dataCheckFile,dataCheckAll;
    public String dataCheckNameAll = "/db.exist";
    public String dataCheckPath = "/am_cache";
    public String Path = Path_sys + dataCheckPath;
    public String[] q = new String[total_circum];

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.epr_p08_ready_layout);

        mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);
        dbReady = mDataBase.getWritableDatabase();

        getCursor();

    }


    /** Обработка базы данных */

    public void getBase () {

        gen_date = inputGen_temp[0];
        gen_time = inputGen_temp[1];

        gen_country = inputGen_temp[2];
        gen_geo = inputGen_temp[3];
        gen_city = inputGen_temp[4];

        q_text1 = inputGen_temp[5];
        q_text2 = inputGen_temp[6];
        q_text3 = inputGen_temp[7];

        wit_text1 = inputGen_temp[8];
        wit_text2 = inputGen_temp[9];
        wit_text3 = inputGen_temp[10];
        wit_text4 = inputGen_temp[11];

        drA_s00_IDA = inputDrA_temp[0];
        drA_s01_Surname = inputDrA_temp[1];
        drA_s02_Name = inputDrA_temp[2];
        drA_s03_Fathername = inputDrA_temp[3];
        drA_s04_Full = inputDrA_temp[4];
        drA_s05_Adress = inputDrA_temp[5];
        drA_s06_Index = inputDrA_temp[6];
        drA_s07_Country = inputDrA_temp[7];
        drA_s08_Phone = inputDrA_temp[8];
        drA_s09_EMail = inputDrA_temp[9];
        drA_s10_Model = inputDrA_temp[10];
        drA_s11_RegSign = inputDrA_temp[11];
        drA_s12_RegCountry = inputDrA_temp[12];
        drA_s13_Switch_Pricep = inputDrA_temp[13];
        drA_s14_RegSign_Pricep = inputDrA_temp[14];
        drA_s15_RegCountry_Pricep = inputDrA_temp[15];
        drA_s16_InsName = inputDrA_temp[16];
        drA_s17_InsRadio = inputDrA_temp[17];
        drA_s21_Seria = inputDrA_temp[18];
        drA_s22_N = inputDrA_temp[19];
        drA_s23_From = inputDrA_temp[20];
        drA_s24_To = inputDrA_temp[21];
        drA_s25_DocCountry = inputDrA_temp[22];
        drA_s26_DocTel = inputDrA_temp[23];
        drA_s27_Switch_Ins = inputDrA_temp[24];
        drA_s28_Surname_Lic = inputDrA_temp[25];
        drA_s29_Name_Lic = inputDrA_temp[26];
        drA_s30_FatherName_Lic = inputDrA_temp[27];
        drA_s31_BirthDay_Lic = inputDrA_temp[28];
        drA_s32_Adress_Lic = inputDrA_temp[29];
        drA_s33_Index_Lic = inputDrA_temp[30];
        drA_s34_Country_Lic = inputDrA_temp[31];
        drA_s35_Phone_Lic = inputDrA_temp[32];
        drA_s36_EMail_Lic = inputDrA_temp[33];
        drA_s37_Series_Lic = inputDrA_temp[34];
        drA_s38_Num_Lic = inputDrA_temp[35];
        drA_s39_Category_Lic = inputDrA_temp[36];
        drA_s40_Valid_Lic = inputDrA_temp[37];
        drA_s41_HitBMP = inputDrA_temp[38];
        drA_s42_damage = inputDrA_temp[39];

        drB_s00_IDA = inputDrB_temp[0];
        drB_s01_Surname = inputDrB_temp[1];
        drB_s02_Name = inputDrB_temp[2];
        drB_s03_Fathername = inputDrB_temp[3];
        drB_s04_Full = inputDrB_temp[4];
        drB_s05_Adress = inputDrB_temp[5];
        drB_s06_Index = inputDrB_temp[6];
        drB_s07_Country = inputDrB_temp[7];
        drB_s08_Phone = inputDrB_temp[8];
        drB_s09_EMail = inputDrB_temp[9];
        drB_s10_Model = inputDrB_temp[10];
        drB_s11_RegSign = inputDrB_temp[11];
        drB_s12_RegCountry = inputDrB_temp[12];
        drB_s13_Switch_Pricep = inputDrB_temp[13];
        drB_s14_RegSign_Pricep = inputDrB_temp[14];
        drB_s15_RegCountry_Pricep = inputDrB_temp[15];
        drB_s16_InsName = inputDrB_temp[16];
        drB_s17_InsRadio = inputDrB_temp[17];
        drB_s21_Seria = inputDrB_temp[18];
        drB_s22_N = inputDrB_temp[19];
        drB_s23_From = inputDrB_temp[20];
        drB_s24_To = inputDrB_temp[21];
        drB_s25_DocCountry = inputDrB_temp[22];
        drB_s26_DocTel = inputDrB_temp[23];
        drB_s27_Switch_Ins = inputDrB_temp[24];
        drB_s28_Surname_Lic = inputDrB_temp[25];
        drB_s29_Name_Lic = inputDrB_temp[26];
        drB_s30_FatherName_Lic = inputDrB_temp[27];
        drB_s31_BirthDay_Lic = inputDrB_temp[28];
        drB_s32_Adress_Lic = inputDrB_temp[29];
        drB_s33_Index_Lic = inputDrB_temp[30];
        drB_s34_Country_Lic = inputDrB_temp[31];
        drB_s35_Phone_Lic = inputDrB_temp[32];
        drB_s36_EMail_Lic = inputDrB_temp[33];
        drB_s37_Series_Lic = inputDrB_temp[34];
        drB_s38_Num_Lic = inputDrB_temp[35];
        drB_s39_Category_Lic = inputDrB_temp[36];
        drB_s40_Valid_Lic = inputDrB_temp[37];
        drB_s41_HitBMP = inputDrB_temp[38];
        drB_s42_damage = inputDrB_temp[39];

        primA = inputSignA_temp[0];
        signA = inputSignA_temp[1];
        nameA = inputSignA_temp[2];
        signA_resp = inputSignA_temp[3];
        nameA_resp = inputSignA_temp[4];

        primB = inputSignB_temp[0];
        signB = inputSignB_temp[1];
        nameB = inputSignB_temp[2];
        signB_resp = inputSignB_temp[3];
        nameB_resp = inputSignB_temp[4];

    }



    /** Сбор значений **/

    private String[] inputGen_temp, inputDrA_temp, inputDrB_temp, inputGenA_temp, inputGenB_temp, inputSignA_temp, inputSignB_temp;
    private String inputScheme_temp;

    public void getCursor () {

        inputGen_temp = new String[total_gen];

        t1_cursor = dbReady.query("t1_general", new String[]{
                        EPR_system_DataBaseContainer.T1_DATE,
                        EPR_system_DataBaseContainer.T1_TIME,
                        EPR_system_DataBaseContainer.T1_COUNTRY,
                        EPR_system_DataBaseContainer.T1_GEO,
                        EPR_system_DataBaseContainer.T1_M_PLACE,
                        EPR_system_DataBaseContainer.T1_Q1,
                        EPR_system_DataBaseContainer.T1_Q2,
                        EPR_system_DataBaseContainer.T1_Q3,
                        EPR_system_DataBaseContainer.T1_W1,
                        EPR_system_DataBaseContainer.T1_W2,
                        EPR_system_DataBaseContainer.T1_W3,
                        EPR_system_DataBaseContainer.T1_W4
                },
                null, null, null, null, null);

        t1_cursor.moveToFirst();
        if (t1_cursor != null && t1_cursor.moveToFirst()) {
//        if (t1_cursor != null) {

            inputGen_temp = new String[] {
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_DATE)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_TIME)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_COUNTRY)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_GEO)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_M_PLACE)),

                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_Q1)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_Q2)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_Q3)),

                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W1)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W2)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W3)),
                    t1_cursor.getString(t1_cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W4))
            };

            t1_cursor.close();
            t1_cursor.moveToFirst();

        }

        inputDrA_temp = new String[total_drA];

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

        t2_cursor = dbReady.query("t2_driverA", t2,
                null, null, null, null, null);

        t2_cursor.moveToFirst();
        if (t2_cursor != null && t2_cursor.moveToFirst()) {
//        if (t2_cursor != null) {

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

        inputDrB_temp = new String[total_drB];

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

        t3_cursor = dbReady.query("t3_driverB", t3,
                null, null, null, null, null);

        t3_cursor.moveToFirst();
        if (t3_cursor != null && t3_cursor.moveToFirst()) {
//        if (t3_cursor != null) {

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

            t3_cursor.close();
            t3_cursor.moveToFirst();

        }

        inputGenA_temp = new String[total_circum];
        inputGenB_temp = new String[total_circum];

        t4A_cursor = dbReady.query("t4_circumA", new String[]{
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
//        if (t4A_cursor != null) {

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

        t4B_cursor = dbReady.query("t4_circumB", new String[]{
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
//        if (t4B_cursor != null) {

            inputGenB_temp = new String[] {
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

        String[] t5 = new String[] {EPR_system_DataBaseContainer.T5_01};

        t5_cursor = dbReady.query("t5_scheme", t5,
                null, null, null, null, null);

        t5_cursor.moveToFirst();
        if (t5_cursor != null && t5_cursor.moveToFirst()) {
//        if (t5_cursor != null) {
            inputScheme_temp = t5_cursor.getString(t5_cursor.getColumnIndex(EPR_system_DataBaseContainer.T5_01));

            t5_cursor.close();
            t5_cursor.moveToFirst();
        }

        inputSignA_temp = new String[total_sign];
        inputSignB_temp = new String[total_sign];

        String[] t6a = new String[]{
                EPR_system_DataBaseContainer.T6A_PRIM,
                EPR_system_DataBaseContainer.T6A_SIGN,
                EPR_system_DataBaseContainer.T6A_NAME,
                EPR_system_DataBaseContainer.T6A_SIGN_RESP,
                EPR_system_DataBaseContainer.T6A_NAME_RESP

        };

        t6a_cursor = dbReady.query("t6a_sign", t6a,
                null, null, null, null, null);

        t6a_cursor.moveToFirst();
        if (t6a_cursor != null && t6a_cursor.moveToFirst()) {
//        if (t6a_cursor != null) {

            inputSignA_temp = new String[] {
                    t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_PRIM)),
                    t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_SIGN)),
                    t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_NAME)),
                    t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_SIGN_RESP)),
                    t6a_cursor.getString(t6a_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6A_NAME_RESP))
            };

            t6a_cursor.close();
            t6a_cursor.moveToFirst();

        }

        String[] t6b = new String[]{
                EPR_system_DataBaseContainer.T6B_PRIM,
                EPR_system_DataBaseContainer.T6B_SIGN,
                EPR_system_DataBaseContainer.T6B_NAME,
                EPR_system_DataBaseContainer.T6B_SIGN_RESP,
                EPR_system_DataBaseContainer.T6B_NAME_RESP

        };

        t6b_cursor = dbReady.query("t6b_sign", t6b,
                null, null, null, null, null);

        t6b_cursor.moveToFirst();
        if (t6b_cursor != null && t6b_cursor.moveToFirst()) {
//        if (t6b_cursor != null) {

            inputSignB_temp = new String[] {
                    t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_PRIM)),
                    t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_SIGN)),
                    t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_NAME)),
                    t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_SIGN_RESP)),
                    t6b_cursor.getString(t6b_cursor.getColumnIndex(EPR_system_DataBaseContainer.T6B_NAME_RESP))
            };

            t6b_cursor.close();
            t6b_cursor.moveToFirst();

        }

    }

    /** Создние превью **/

    public void getPreviews () {

        assert previewGeneral != null;
        db_general = "Дата ДТП:    " + gen_date
                + "\nВремя ДТП:    " + gen_time
                + "\nСтрана ДТП:    " + gen_country
                + "\nМесто ДПТ:    " + gen_geo
                + "\nМесто ДТП:    " + gen_city
                + "\nПострадавшие?    " + q_text1
                + "\nВред транспорту?    " + q_text2
                + "\nВред имуществу?    " + q_text3
                + "\nСвидетель 1:    " + wit_text1
                + "\nСвидетель 2:    " + wit_text2
                + "\nСвидетель 3:    " + wit_text3
                + "\nСвидетель 4:   " + wit_text4;

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

        assert previewCircum != null;
        String shortPreview = "";
        String[] forPreview = new String[total_circum];

        for (int i=0; i < total_circum; i++) {
            if (inputGenA_temp[i] == null) {
                inputGenA_temp[i] = no;
            }
            if (inputGenB_temp[i] == null) {
                inputGenB_temp[i] = no;
            }
            forPreview[i] = "\n" +q[i] + textTSA + inputGenA_temp[i] +
                    textTSB + inputGenB_temp[i];
            if (inputGenA_temp[i] == yes || inputGenB_temp[i] == yes) {
                shortPreview = shortPreview + "\n" + forPreview[i];
            }
        }

        int sumA = 0, sumB = 0;
        int[] circumA = new int[total_circum], circumB = new int[total_circum];
        for (int i=0; i < total_circum; i++) {
            if (inputGenA_temp[i] == no) {
                circumA[i] = 0;
            } else {
                circumA[i] = 1;
            }
            if (inputGenB_temp[i] == no) {
                circumB[i] = 0;
            } else {
                circumB[i] = 1;
            }
            sumA += circumA[i];
            sumB += circumB[i];
        }

        db_circum = shortPreview +
                "\n\nКоличество отмеченных пунктов:" +
                textTSA + sumA +
                textTSB + sumB;

        assert previewScheme != null;
        db_Scheme = "Схема ДТП   \n";

        previewPrimA = "Примечание водителя ТС A\n" + primA;
        previewNameA = nameA;
        previewNameA_resp = nameA_resp;
        previewPrimB = "Примечание водителя ТС B\n" + primB;
        previewNameB = nameB;
        previewNameB_resp = nameB_resp;

    }

    /** Конец Обработка базы данных **/
    public static final String FONTDIR = "resources/fonts";

    public void createPDF( ) {

        getBase();
        getPreviews();
        Document doc = new Document();
        doc.setPageSize(PageSize.A4);

        try {

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/auto-mobile";

            Font font1 = FontFactory.getFont("Helvetica", "CP1251", BaseFont.EMBEDDED);


            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

            Log.d("PDFCreator", "PDF Path: " + path);

            path_path = path;

            String gen_date_to_name = gen_date.replaceAll("\\.", "-");
            String gen_time_to_name = gen_time.replaceAll(":", "-");

            file_name = "dtp_" + gen_date_to_name + "_" + gen_time_to_name + ".pdf";

            File file = new File(dir, file_name);
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter writer = PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();
            writer.setSpaceCharRatio(PdfWriter.SPACE);
            PdfContentByte cb = writer.getDirectContent();

            // Borders, normally much more complex
            float[] leftBorder = {0,0,0, PageSize.A4.getHeight()};
            float[] rightBorder =
                    {PageSize.A4.getWidth(),0,PageSize.A4.getWidth(),PageSize.A4.getHeight()};

            ColumnText ct = new ColumnText(cb);

            ct.setColumns(leftBorder, rightBorder);

            ct.setYLine(PageSize.A4.getHeight() - 100f);

            ct.setSpaceCharRatio(10f);


            FontFactory.registerDirectory(FONTDIR);


            Paragraph p1 = new Paragraph(String.format(db_general), font1);
            p1.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p1.setIndentationLeft(200);
//	        p1.setIndentationRight(20);
            p1.setAlignment(Paragraph.ALIGN_LEFT);
            p1.setFont(font1);

            //add paragraph to document
            doc.add(p1);


            Paragraph p2 = new Paragraph(String.format(db_driverA), font1);
            p2.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p2.setIndentationLeft(200);
//	        p2.setIndentationRight(20);
            p2.setAlignment(Paragraph.ALIGN_LEFT);
            p2.setFont(font1);

            //add paragraph to document
            doc.add(p2);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmapOrgn = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.logo);
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapOrgn, 100, 100, false);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg = Image.getInstance(stream.toByteArray());
            myImg.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg);


            Paragraph p3 = new Paragraph(String.format(db_driverB), font1);
            p3.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p3.setIndentationLeft(200);
//	        p3.setIndentationRight(20);
            p3.setAlignment(Paragraph.ALIGN_LEFT);
            p3.setFont(font1);

            //add paragraph to document
            doc.add(p3);

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg2 = Image.getInstance(stream.toByteArray());
            myImg2.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg2);


            Paragraph p4 = new Paragraph(String.format(db_circum), font1);
            p4.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p4.setIndentationLeft(200);
//	        p4.setIndentationRight(20);
            p4.setAlignment(Paragraph.ALIGN_LEFT);
            p4.setFont(font1);

            //add paragraph to document
            doc.add(p4);


            Paragraph p5 = new Paragraph(String.format(previewPrimA), font1);
            p5.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p5.setIndentationLeft(200);
//	        p5.setIndentationRight(20);
            p5.setAlignment(Paragraph.ALIGN_LEFT);
            p5.setFont(font1);

            //add paragraph to document
            doc.add(p5);

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg3 = Image.getInstance(stream.toByteArray());
            myImg3.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg3);


            Paragraph p6 = new Paragraph(String.format(previewNameA), font1);
            p6.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p6.setIndentationLeft(200);
//	        p6.setIndentationRight(20);
            p6.setAlignment(Paragraph.ALIGN_LEFT);
            p6.setFont(font1);

            //add paragraph to document
            doc.add(p6);

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg4 = Image.getInstance(stream.toByteArray());
            myImg4.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg4);


            Paragraph p7 = new Paragraph(String.format(previewNameA_resp), font1);
            p7.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p7.setIndentationLeft(200);
//	        p7.setIndentationRight(20);
            p7.setAlignment(Paragraph.ALIGN_LEFT);
            p7.setFont(font1);

            //add paragraph to document
            doc.add(p7);


            Paragraph p8 = new Paragraph(String.format(previewPrimB), font1);
            p8.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p8.setIndentationLeft(200);
//	        p8.setIndentationRight(20);
            p8.setAlignment(Paragraph.ALIGN_LEFT);
            p8.setFont(font1);

            //add paragraph to document
            doc.add(p8);

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg5 = Image.getInstance(stream.toByteArray());
            myImg5.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg5);


            Paragraph p9 = new Paragraph(String.format(previewNameA), font1);
            p9.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p9.setIndentationLeft(200);
//	        p9.setIndentationRight(20);
            p9.setAlignment(Paragraph.ALIGN_LEFT);
            p9.setFont(font1);

            //add paragraph to document
            doc.add(p9);

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg6 = Image.getInstance(stream.toByteArray());
            myImg6.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg6);


            Paragraph p10 = new Paragraph(String.format(previewNameA_resp), font1);
            p10.setAlignment(Element.ALIGN_JUSTIFIED);
//	        p10.setIndentationLeft(200);
//	        p10.setIndentationRight(20);
            p10.setAlignment(Paragraph.ALIGN_LEFT);
            p10.setFont(font1);

            //add paragraph to document
            doc.add(p7);



        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally
        {
            doc.close();
        }

    }

    public void showPreview() {

        getBase();
        getPreviews();

        previewGeneral = (TextView) findViewById(R.id.testBase_gen);
        previewGeneral.setText(db_general, TextView.BufferType.EDITABLE);
        previewGeneral.setVisibility((previewGeneral.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewDriverA = (TextView)findViewById(R.id.testBase_drA);
        previewDriverA.setText(db_driverA, TextView.BufferType.EDITABLE);
        previewDriverA.setVisibility((previewDriverA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewImgDrA = (ImageView)findViewById(R.id.testBaseImg_drA);
        previewImgDrA.setImageResource(R.drawable.logo); /** Временное явление */
        previewImgDrA.setVisibility((previewImgDrA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewDriverB = (TextView)findViewById(R.id.testBase_drB);
        previewDriverB.setText(db_driverB, TextView.BufferType.EDITABLE);
        previewDriverB.setVisibility((previewDriverB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewImgDrB = (ImageView)findViewById(R.id.testBaseImg_drB);
        previewImgDrB.setImageResource(R.drawable.logo); /** Временное явление */
        previewImgDrB.setVisibility((previewImgDrB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewCircum = (TextView)findViewById(R.id.testBase_circum);
        previewCircum.setText(db_circum, TextView.BufferType.EDITABLE);
        previewCircum.setVisibility((previewCircum.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewScheme = (TextView)findViewById(R.id.testBase_scheme);
        previewScheme.setText(db_Scheme, TextView.BufferType.EDITABLE);
        previewScheme.setVisibility((previewScheme.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewImg_scheme = (ImageView)findViewById(R.id.testBaseImg_scheme);
        previewImg_scheme.setImageResource(R.drawable.logo); /** Временное явление */
        previewImg_scheme.setVisibility((previewImg_scheme.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        preview_primA = (TextView)findViewById(R.id.testBase_primA);
        preview_primA.setText(previewPrimA, TextView.BufferType.EDITABLE);
        preview_primA.setVisibility((preview_primA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameA = (TextView)findViewById(R.id.testBase_nameA);
        preview_nameA.setText(previewNameA, TextView.BufferType.EDITABLE);
        preview_nameA.setVisibility((preview_nameA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameA_resp = (TextView)findViewById(R.id.testBase_nameA_resp);
        preview_nameA_resp.setText(previewNameA_resp, TextView.BufferType.EDITABLE);
        preview_nameA_resp.setVisibility((preview_nameA_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_primB = (TextView)findViewById(R.id.testBase_primB);
        preview_primB.setText(previewPrimB, TextView.BufferType.EDITABLE);
        preview_primB.setVisibility((preview_primB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameB = (TextView)findViewById(R.id.testBase_nameB);
        preview_nameB.setText(previewNameB, TextView.BufferType.EDITABLE);
        preview_nameB.setVisibility((preview_nameB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameB_resp = (TextView)findViewById(R.id.testBase_nameB_resp);
        preview_nameB_resp.setText(previewNameB_resp, TextView.BufferType.EDITABLE);
        preview_nameB_resp.setVisibility((preview_nameB_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewImgSignA = (ImageView)findViewById(R.id.testBase_signA);
        previewImgSignA.setImageResource(R.drawable.logo); /** Временное явление */
        previewImgSignA.setVisibility((previewImgSignA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgSignA_resp = (ImageView)findViewById(R.id.testBase_signA_resp);
        previewImgSignA_resp.setImageResource(R.drawable.logo); /** Временное явление */
        previewImgSignA_resp.setVisibility((previewImgSignA_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgSignB = (ImageView)findViewById(R.id.testBase_signB);
        previewImgSignB.setImageResource(R.drawable.logo); /** Временное явление */
        previewImgSignB.setVisibility((previewImgSignB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgSignB_resp = (ImageView)findViewById(R.id.testBase_signB_resp);
        previewImgSignB_resp.setImageResource(R.drawable.logo); /** Временное явление */
        previewImgSignB_resp.setVisibility((previewImgSignB_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        closePreview = (TextView)findViewById(R.id.preview_close);
        closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewScroll = (ScrollView)findViewById(R.id.testBaseScroll);
        previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewTable = (TableLayout)findViewById(R.id.testBaseTable);
        previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRow_gen = (TableRow)findViewById(R.id.testBaseRow_gen);
        previewRow_gen.setVisibility((previewRow_gen.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_drA1 = (TableRow)findViewById(R.id.testBaseRow_drA1);
        testBaseRow_drA1.setVisibility((testBaseRow_drA1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_drA2 = (TableRow)findViewById(R.id.testBaseRow_drA2);
        testBaseRow_drA2.setVisibility((testBaseRow_drA2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_drB1 = (TableRow)findViewById(R.id.testBaseRow_drB1);
        testBaseRow_drB1.setVisibility((testBaseRow_drB1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_drB2 = (TableRow)findViewById(R.id.testBaseRow_drB2);
        testBaseRow_drB2.setVisibility((testBaseRow_drB2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_circum = (TableRow)findViewById(R.id.testBaseRow_circum);
        testBaseRow_circum.setVisibility((testBaseRow_circum.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_scheme1 = (TableRow)findViewById(R.id.testBaseRow_scheme1);
        testBaseRow_scheme1.setVisibility((testBaseRow_scheme1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        testBaseRow_scheme2 = (TableRow)findViewById(R.id.testBaseRow_scheme2);
        testBaseRow_scheme2.setVisibility((testBaseRow_scheme2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRow1a = (TableRow)findViewById(R.id.testBaseRow1a);
        previewRow1a.setVisibility((previewRow1a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow2a = (TableRow)findViewById(R.id.testBaseRow2a);
        previewRow2a.setVisibility((previewRow2a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow3a = (TableRow)findViewById(R.id.testBaseRow3a);
        previewRow3a.setVisibility((previewRow3a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow4a = (TableRow)findViewById(R.id.testBaseRow4a);
        previewRow4a.setVisibility((previewRow4a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow5a = (TableRow)findViewById(R.id.testBaseRow5a);
        previewRow5a.setVisibility((previewRow5a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRowSpace = (TableRow)findViewById(R.id.testBaseRowSpace);
        previewRowSpace.setVisibility((previewRowSpace.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRow1b = (TableRow)findViewById(R.id.testBaseRow1b);
        previewRow1b.setVisibility((previewRow1b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow2b = (TableRow)findViewById(R.id.testBaseRow2b);
        previewRow2b.setVisibility((previewRow2b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow3b = (TableRow)findViewById(R.id.testBaseRow3b);
        previewRow3b.setVisibility((previewRow3b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow4b = (TableRow)findViewById(R.id.testBaseRow4b);
        previewRow4b.setVisibility((previewRow4b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow5b = (TableRow)findViewById(R.id.testBaseRow5b);
        previewRow5b.setVisibility((previewRow5b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        Toast.makeText(getApplicationContext(),
                "Предварительный просмотр", Toast.LENGTH_SHORT).show();

    }

    public void btnShow (View view) {
        showPreview();
    }

    public void btnEdit (View view) {
        Intent intentAccident = new Intent(this, EPR_p01_Accident.class);
        startActivity(intentAccident);
    }

    public void btnSave (View view) {

        createPDF();

        String Saved = "Сохранено!\n" + path_path + "/" + file_name;

//        SaveTxt = (TextView) findViewById(R.id.btnSave);
//        SaveTxt.setText(Saved);

	    Toast.makeText(getApplicationContext(),
			    Saved, Toast.LENGTH_LONG).show();

    }

    public void sentPDF () {

        String[] mailto = {"art.litvinko@gmail.com"};
        Uri uri = Uri.parse("/droidText");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Протокол ДТП");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Сформировано с помощью приложения Авто Mobile");
        emailIntent.setType("application/pdf");
//        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(emailIntent, "Отправить с помощью:"));

    }

    public void btnSend (View view) {

        sentPDF();

        String Sent = "Всё прошло хорошо!\nУдачи на дорогах";

//        SendTxt = (TextView) findViewById(R.id.btnSend);
//        SendTxt.setText(Sent);

	    Toast.makeText(getApplicationContext(),
			    Sent, Toast.LENGTH_LONG).show();

    }

    public void gotoSignPage (View view) {

        ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


        Intent intentSignPage = new Intent(this, EPR_p07_SignPage.class);
        startActivity(intentSignPage);

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

                showPreview();
                break;

            case R.id.action_clear_db:

                ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");

                File dbSelf = new File("/data/data/com.mainfish.europrotocola04/databases/am_protocol.db");
                dbSelf.delete();

//                dataCheckFile.delete();
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

        previewGeneral.setVisibility((previewGeneral.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewDriverA.setVisibility((previewDriverA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgDrA.setVisibility((previewImgDrA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewDriverB.setVisibility((previewDriverB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgDrB.setVisibility((previewImgDrB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewCircum.setVisibility((previewCircum.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewScheme.setVisibility((previewScheme.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImg_scheme.setVisibility((previewImg_scheme.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        preview_primA.setVisibility((preview_primA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameA.setVisibility((preview_nameA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameA_resp.setVisibility((preview_nameA_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_primB.setVisibility((preview_primB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameB.setVisibility((preview_nameB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        preview_nameB_resp.setVisibility((preview_nameB_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewImgSignA.setVisibility((previewImgSignA.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgSignA_resp.setVisibility((previewImgSignA_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgSignB.setVisibility((previewImgSignB.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImgSignB_resp.setVisibility((previewImgSignB_resp.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow_gen.setVisibility((previewRow_gen.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_drA1.setVisibility((testBaseRow_drA1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_drA2.setVisibility((testBaseRow_drA2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_drB1.setVisibility((testBaseRow_drB1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_drB2.setVisibility((testBaseRow_drB2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_circum.setVisibility((testBaseRow_circum.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_scheme1.setVisibility((testBaseRow_scheme1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        testBaseRow_scheme2.setVisibility((testBaseRow_scheme2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRow1a.setVisibility((previewRow1a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow2a.setVisibility((previewRow2a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow3a.setVisibility((previewRow3a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow4a.setVisibility((previewRow4a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow5a.setVisibility((previewRow5a.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRowSpace.setVisibility((previewRowSpace.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

        previewRow1b.setVisibility((previewRow1b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow2b.setVisibility((previewRow2b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow3b.setVisibility((previewRow3b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow4b.setVisibility((previewRow4b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow5b.setVisibility((previewRow5b.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

    }

}
