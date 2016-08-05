package com.mainfish.europrotocola04;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by artli on 03.03.2016.
 */
public class EPR_p01_Accident extends AppCompatActivity {

    public TextView redI;

    public boolean mSlideState;

    private String[] infoText;
    private ListView mDrawerListView;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.epr_p01_accident);


        String styledText = "Нужно последовательно отвечать на вопросы, следуя простым инструкциям. Подсказки по заполнению - <br/>под кнопкой <b><font color=\"#ff0000\">i</font></b>";
        redI = (TextView) findViewById(R.id.textAccident22);
        redI.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

        infoText = getResources().getStringArray(R.array.info_button);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.epr_system_info_drawer, infoText));



        mDrawerLayout = (DrawerLayout) findViewById(R.id.epr_p01_accident);
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

    public void accident_generalClick (View view) {

        Intent intentGeneral = new Intent(this, EPR_p02_General.class);
        startActivity(intentGeneral);

    }

    public void HomeClick (View view) {

        Intent intentHome = new Intent(this, EPR_home_AutoMobile.class);
        startActivity(intentHome);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
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


}