package com.mainfish.europrotocola04;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by designer on 10/06/16.
 */
public class EPR_p02a_PageError extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.epr_p02a_page_error);

    }

    public void gotoGeneral (View view) {

        Intent intentGeneral = new Intent(this, EPR_p02_General.class);
        startActivity(intentGeneral);

    }

    public void gotoHome (View view) {

        Intent intentHome = new Intent(this, EPR_home_AutoMobile.class);
        startActivity(intentHome);

    }

}
