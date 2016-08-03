package com.mainfish.europrotocola04;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by artli on 03.03.2016.
 */
public class Accident extends AppCompatActivity {

    public TextView redI;

    public boolean mSlideState;

    private String[] infoText;
    private ListView mDrawerListView;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.accident);


        String styledText = "Нужно последовательно отвечать на вопросы, следуя простым инструкциям. Подсказки по заполнению - <br/>под кнопкой <b><font color=\"#ff0000\">i</font></b>";
        redI = (TextView) findViewById(R.id.textAccident22);
        redI.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

        infoText = getResources().getStringArray(R.array.info_button);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.info_drawer_layout, infoText));



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_accident);
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

        Intent intentGeneral = new Intent(this, General.class);
        startActivity(intentGeneral);

    }

    public void HomeClick (View view) {

        Intent intentHome = new Intent(this, AutoMobile.class);
        startActivity(intentHome);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.info_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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