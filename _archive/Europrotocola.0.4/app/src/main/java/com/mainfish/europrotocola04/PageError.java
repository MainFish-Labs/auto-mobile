package com.mainfish.europrotocola04;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by designer on 10/06/16.
 */
public class PageError  extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.page_error);

    }

    public void gotoGeneral (View view) {

        Intent intentGeneral = new Intent(this, General.class);
        startActivity(intentGeneral);

    }

    public void gotoHome (View view) {

        Intent intentHome = new Intent(this, AutoMobile.class);
        startActivity(intentHome);

    }

}
