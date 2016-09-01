package com.mainfish.europrotocola04;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by artli on 04.03.2016.
 */
public class EPR_home_AutoMobile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.epr_home_menu_auto_mobile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_preview) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void infoClick (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentInfo = new Intent(this, EPR_info_InfoPage.class);
        startActivity(intentInfo);

    }

    public void contactsClick (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentContacts = new Intent(this, EPR_cont_Contacts.class);
        startActivity(intentContacts);

    }

    public void accidentClick (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentAccident = new Intent(this, EPR_p01_Accident.class);
        startActivity(intentAccident);

    }

    public void drawHitA (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentDrawHitA = new Intent(this, xEPR_DrawHitActivity.class);
        startActivity(intentDrawHitA);

    }

    public void drawScheme (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentDrawSchemeA = new Intent(this, xEPR_DrawSchemeActivity.class);
        startActivity(intentDrawSchemeA);

    }

    public void drawSign (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentDrawSignA = new Intent(this, xEPR_DrawSignActivity.class);
        startActivity(intentDrawSignA);

    }

    public void gotoCircum (View view) {

	    ProgressDialog.show(this, "Загрузка", "Идёт обращение к базе данных...");


	    Intent intentCircum = new Intent(this, EPR_p08_ReadyPage.class);
        startActivity(intentCircum);

    }

}
