package com.mainfish.europrotocola04;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

    public String path_path, file_name, db_general = " ";

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.epr_p08_ready_layout);

	    mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);

	    mSQLiteDatabase = mDataBase.getWritableDatabase();

    }


    /** Обработка базы данных */

    public Cursor cursor; // !!!!

    private EPR_system_DataBaseContainer mDataBase;
    private SQLiteDatabase mSQLiteDatabase;

    public String gen_date = "", gen_time = "", gen_country = "", gen_geo = "", gen_city = "";
    public String textQ1 = "Нет", textQ2 = "Нет", textQ3 = "Нет", q_text1 = "Нет", q_text2 = "Нет", q_text3 = "Нет";
    public String wit_text1 = "", wit_text2 = "", wit_text3 = "", wit_text4 = "";

    public void getBase () {

        getCursor();

        handleCursor();

    }

    public void getCursor () {

        cursor = mSQLiteDatabase.query("am_protocol", new String[] {
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

    }

    public void handleCursor () {

        cursor.moveToFirst();
        if( cursor != null && cursor.moveToFirst() ) {
            gen_date = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_DATE));
            gen_time = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_TIME));
            gen_country = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_COUNTRY));
            gen_geo = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_GEO));
            gen_city = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_M_PLACE));

            q_text1 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_Q1));
            q_text2 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_Q2));
            q_text3 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_Q3));

            wit_text1 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W1));
            wit_text2 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W2));
            wit_text3 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W3));
            wit_text4 = cursor.getString(cursor.getColumnIndex(EPR_system_DataBaseContainer.T1_W4));

            TextView testBaseView = (TextView) findViewById(R.id.testBase);
            assert testBaseView != null;
            testBaseView.setText(db_general, TextView.BufferType.EDITABLE);

            cursor.close();
            cursor.moveToFirst();

        }

    }

    /** Конец Обработка базы данных **/
    public static final String FONTDIR = "resources/fonts";

    public void createPDF( ) {

        getBase();
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

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmapOrgn = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.logo);
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapOrgn, 100, 100, false);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , stream);
            Image myImg = Image.getInstance(stream.toByteArray());
            myImg.setAlignment(Image.LEFT);

            //add image to document
            doc.add(myImg);



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

    public void btnSave (View view) {

        createPDF();

        String Saved = "Сохранено!\n" + path_path + "/" + file_name;

        SaveTxt = (TextView) findViewById(R.id.btnSave);
        SaveTxt.setText(Saved);

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

        SendTxt = (TextView) findViewById(R.id.btnSend);
        SendTxt.setText(Sent);

    }

    public void gotoSignPage (View view) {

        Intent intentSignPage = new Intent(this, EPR_p07_SignPage.class);
        startActivity(intentSignPage);

    }

}