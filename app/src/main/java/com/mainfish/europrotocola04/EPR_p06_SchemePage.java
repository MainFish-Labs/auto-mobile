package com.mainfish.europrotocola04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by artli on 12.05.2016.
 */
public class EPR_p06_SchemePage extends AppCompatActivity {

    private String[] infoText;
    private ListView mDrawerListView;

    public boolean mSlideState;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.scheme_layout);

        infoText = getResources().getStringArray(R.array.info_button5);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.info_drawer_layout, infoText));

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

        Intent intentSignPage = new Intent(this, EPR_p07_SignPage.class);
        startActivity(intentSignPage);

    }

    public void gotoCircum (View view) {

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

}