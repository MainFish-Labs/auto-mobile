package com.mainfish.europrotocola04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by artli on 05.03.2016.
 */
public class EPR_p04_DriverB extends AppCompatActivity {

    private String[] infoText, insuranseText, insuranseTextFull, insuranseTextTel, insuranseTextEmail;
    private ListView mDrawerListView, mDrawerListView_insuranse;

    public boolean mSlideState, mSlideStateIns;
    private DrawerLayout mDrawerLayout, mDrawerIns;

    public TextView InsChooseB;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.epr_p04_driver_b);

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

        if (savedInstanceState == null) {
            selectItem(0);

            String Chosen = "";

            InsChooseB = (TextView) findViewById(R.id.driver_b_insurance_name);
            InsChooseB.setText(Chosen);
        }

    }

    //  Слушатель для элементов списка в выдвижной панели
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),
                    "Выбрана компания " + insuranseText[position], Toast.LENGTH_SHORT).show();
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // обновим выбранный элемент списка и закрываем панель
        mDrawerListView_insuranse.setItemChecked(position, true);

        String Chosen = insuranseTextFull[position] + "\n" + insuranseTextTel[position] + "\n" + insuranseTextEmail[position];

        InsChooseB = (TextView) findViewById(R.id.driver_b_insurance_name);
        InsChooseB.setText(Chosen);
        mDrawerIns.closeDrawer(mDrawerListView_insuranse);
    }

    public void gotoCircum (View view) {

        Intent intentCircum = new Intent(this, EPR_p05_Circumstances.class);
        startActivity(intentCircum);

    }

    public void gotoDriverA (View view) {

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

}