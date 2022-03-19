package com.example.materialscalculator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    // Declare each button for each activity
    private Button BtnSlab;
    private Button BtnStairs;
    private Button BtnCurb;
    private Button BtnBrick;
    private Button BtnBlock;
    private Button BtnWall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This shows the name, sub name, and icon image on the  Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Materials Calculator");
        actionBar.setIcon(R.drawable.ic_subpic);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Initialize each button for each page
        BtnSlab = findViewById(R.id.slab);
        BtnStairs = findViewById(R.id.stairs);
        BtnCurb = findViewById(R.id.curb);
        BtnBlock = findViewById(R.id.Block);
        BtnBrick = findViewById(R.id.Brick);
        BtnWall = findViewById((R.id.Wall));

        // Opens the Slab activity
        BtnSlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Slab.class);
                startActivity(intent);
            }
        });

        // Open the Stairs activity
        BtnStairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Stairs.class);
                startActivity(intent);
            }
        });
        // Open the Curb Activity
        BtnCurb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Curb.class);
                startActivity(intent);
            }
        });
        //Opens the Wall activity
        BtnWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Wall.class);
                startActivity(intent);
            }
        });
        // Opens the Block activity
        BtnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Block.class);
                startActivity(intent);
            }
        });
        BtnBrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Brick.class);
                startActivity(intent);
            }
        });
        // TODO: 3/18/2022 Work on the interface in order to get the user input
    }
    // This adds our custom tool bar settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    // These are the navigation instructions for the toolbar selections
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(Home.this, Home.class));
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