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
public class EPR_p06_SchemePage extends AppCompatActivity {

    private String[] infoText;
    private ListView mDrawerListView;

    public boolean mSlideState;
    private DrawerLayout mDrawerLayout;

	/** Переменные полей ввода */

	public ImageView SchemeDTP;

	/** Переменные базы данных */

	public Cursor t5_cursor; // !!!!

	public String yes = "Да", no = "Нет", empty = "";

	private EPR_system_DataBaseContainer mDataBase;
	private SQLiteDatabase dbScheme;
	private String db_Scheme, db_Scheme_name = "t5_scheme";
	private TextView previewScheme, closePreview;
	private ScrollView previewScroll;
	private ImageView previewImg;
	private TableLayout previewTable;
	private TableRow previewRow1, previewRow2;

	public String schemeImage;

	public File Path_sys = android.os.Environment.getExternalStorageDirectory();
	public File dataCheckFile,dataCheckAll;
	public String dataCheckNameScheme = "/db_scheme.exist";
	public String dataCheckNameAll = "/db.exist";
	public String dataCheckPath = "/am_cache";
	public String Path = Path_sys + dataCheckPath;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.epr_p06_scheme_layout);

	    mDataBase = new EPR_system_DataBaseContainer(this, "am_protocol.db", null, 1);
	    dbScheme = mDataBase.getWritableDatabase();

	    dataCheckAll = new File(Path, dataCheckNameAll);
	    dataCheckFile = new File(Path, dataCheckNameScheme);
	
	    SchemeDTP = (ImageView) findViewById(R.id.schemeDTP);

        setUpDrawers();
	    handleScheme();

	    if (dataCheckFile.exists() && dataCheckAll.exists()) {
		    getCursor();
	    }

    }

	/** Обработка Места удара */

	public void handleScheme () {

		if (!dataCheckFile.exists()) {
			SchemeDTP.setImageResource(R.drawable.logo);
		}
		SchemeDTP.setImageResource(R.drawable.logo); /** После добавления модуля рисования - исправить */

	}
	
	
	
	/** Вставка сохранённых значений **/
	
	private String inputScheme_temp;
	
	public void getCursor () {
		
		String[] t5 = new String[] {EPR_system_DataBaseContainer.T5_01};
		
		t5_cursor = dbScheme.query(db_Scheme_name, t5,
				null, null, null, null, null);
		
		t5_cursor.moveToFirst();
		if (t5_cursor != null && t5_cursor.moveToFirst()) {
			inputScheme_temp = t5_cursor.getString(t5_cursor.getColumnIndex(EPR_system_DataBaseContainer.T5_01));
		}
		
		t5_cursor.close();
		t5_cursor.moveToFirst();
		
		schemeImage = inputScheme_temp;
		
		SchemeDTP.setImageDrawable(Drawable.createFromPath(schemeImage));
		
	}
	
	/** Конец Вставка сохранённых значений **/
	
	/** Обработка базы данных */
	
	public void getStrings () {
		
		//schemeImage = 
		
	}
	
	public void getBase () {
		
		//getStrings();
		
		ContentValues t5_values = new ContentValues();
		
		// Значения столбцов
		t5_values.put(EPR_system_DataBaseContainer.T5_01, schemeImage);
		
		// Вставляем данные в таблицу
		
		if (dataCheckFile.exists() && dataCheckAll.exists()) {
			dbScheme.update(
					db_Scheme_name,
					t5_values,
					EPR_system_DataBaseContainer.T5_01 + "= ?",
					new String[]{
							schemeImage
					}
			);
		} else {
			dbScheme.insert(
					db_Scheme_name,
					null,
					t5_values
			);
			checkFileCreate();
		}
		
		assert previewScheme != null;
		db_Scheme = "Схема ДТП   \n";
		
	}
	
	public void checkFileCreate () {
		
		String sdState = android.os.Environment.getExternalStorageState();
		if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			dataCheckFile = new File(Path_sys, dataCheckPath + dataCheckNameScheme);
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

        infoText = getResources().getStringArray(R.array.info_button5);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.epr_system_info_drawer, infoText));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_scheme);
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

    public void gotoSignPage (View view) {
        getBase();

        Intent intentSignPage = new Intent(this, EPR_p07_SignPage.class);
        startActivity(intentSignPage);

    }

    public void gotoCircum (View view) {
        getBase();

        Intent intentCircum = new Intent(this, EPR_p05_Circumstances.class);
        startActivity(intentCircum);

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
                previewScheme = (TextView)findViewById(R.id.testBase);
	            previewScheme.setText(db_Scheme, TextView.BufferType.EDITABLE);
	            previewScheme.setVisibility((previewScheme.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
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

                File oldBaseDrA = new File(Path, "/db_scheme.exist");
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

	    previewScheme.setVisibility((previewScheme.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        closePreview.setVisibility((closePreview.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewScroll.setVisibility((previewScroll.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewImg.setVisibility((previewImg.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewTable.setVisibility((previewTable.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow1.setVisibility((previewRow1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
        previewRow2.setVisibility((previewRow2.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

    }

}