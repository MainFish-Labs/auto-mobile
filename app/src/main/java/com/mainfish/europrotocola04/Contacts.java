package com.mainfish.europrotocola04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by artli on 03.03.2016.
 */
public class Contacts  extends AppCompatActivity {
    private String[] mCatTitles;
    private ListView mDrawerListView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.contacts);

        mCatTitles = getResources().getStringArray(R.array.contacts_array);
        mDrawerListView = (ListView) findViewById(R.id.rigth_sos);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.contacts_list, mCatTitles));
    }

}
