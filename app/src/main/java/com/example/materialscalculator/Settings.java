package com.example.materialscalculator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //This shows the name/subname, and icon image on the  Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Settings");
        actionBar.setIcon(R.drawable.ic_subpic);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }
    // This is the code used to display the tool bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),Home.class));
                break;
            case R.id.history:
                startActivity(new Intent(getApplicationContext(),History.class));
                break;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(),Settings.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}