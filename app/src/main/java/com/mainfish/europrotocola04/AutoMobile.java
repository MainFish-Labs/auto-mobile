package com.mainfish.europrotocola04;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by artli on 04.03.2016.
 */
public class AutoMobile  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_mobile);
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

        Intent intentInfo = new Intent(this, info.class);
        startActivity(intentInfo);

    }

    public void contactsClick (View view) {

        Intent intentContacts = new Intent(this, Contacts.class);
        startActivity(intentContacts);

    }

    public void accidentClick (View view) {

        Intent intentAccident = new Intent(this, Accident.class);
        startActivity(intentAccident);

    }

    public void drawHitA (View view) {

        Intent intentDrawHitA = new Intent(this, DrawHitActivity.class);
        startActivity(intentDrawHitA);

    }

    public void drawScheme (View view) {

        Intent intentDrawSchemeA = new Intent(this, DrawSchemeActivity.class);
        startActivity(intentDrawSchemeA);

    }

    public void drawSign (View view) {

        Intent intentDrawSignA = new Intent(this, DrawSignActivity.class);
        startActivity(intentDrawSignA);

    }

    public void gotoCircum (View view) {

        Intent intentCircum = new Intent(this, ReadyPage.class);
        startActivity(intentCircum);

    }

}
