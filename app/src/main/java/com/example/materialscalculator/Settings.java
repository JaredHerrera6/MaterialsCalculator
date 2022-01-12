package com.example.materialscalculator;

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
        setContentView(R.layout.Settings);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Settings");
        actionBar.setIcon(R.drawable.ic_subpic);
        actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this,"Home Clicked", Toast.LENGTH_SHORT).show();

                break;
            case R.id.history:
                Toast.makeText(this,"History Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"Settings Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}