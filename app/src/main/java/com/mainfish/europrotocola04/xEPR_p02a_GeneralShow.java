package com.mainfish.europrotocola04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by artli on 28.03.2016.
 */
public class xEPR_p02a_GeneralShow extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.general_show);

        String getDate = getIntent().getExtras().getString("getDate");

        TextView showDate = (TextView)findViewById(R.id.gen_show_date);
        assert showDate != null;
        showDate.setText(getDate);

        String getTime = getIntent().getExtras().getString("getTime");

        TextView showTime = (TextView)findViewById(R.id.gen_show_time);
        showTime.setText(getTime);

        String getPlace = getIntent().getExtras().getString("getPlace");

        TextView showPlace = (TextView)findViewById(R.id.gen_show_place);
        showPlace.setText(getPlace);

        String getCity = getIntent().getExtras().getString("getCity");

        TextView showCity = (TextView)findViewById(R.id.gen_show_city);
        showCity.setText(getCity);

        String getStreet = getIntent().getExtras().getString("getStreet");

        TextView showStreet = (TextView)findViewById(R.id.gen_show_street);
        showStreet.setText(getStreet);

        String getQ1 = getIntent().getExtras().getString("getQ1");

        TextView Q1 = (TextView) findViewById(R.id.gen_show_q1);
        Q1.setText(getQ1);

    }
}
