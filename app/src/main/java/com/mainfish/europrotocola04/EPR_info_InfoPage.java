package com.mainfish.europrotocola04;

/**
 * Created by artli on 03.03.2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class EPR_info_InfoPage extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.info);

        Toolbar infoToolbar = (Toolbar) findViewById(R.id.infoToolbar);
        setSupportActionBar(infoToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setCustomView(R.layout.info);

        TextView infoTitle = (TextView) findViewById(R.id.infoTitle);
        infoTitle.setText(R.string.about_title);

    }

}
