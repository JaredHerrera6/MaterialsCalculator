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
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Stairs extends AppCompatActivity implements View.OnClickListener{
    private Button _Do;
    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat dfQuote =  new DecimalFormat("$###,###,##0.00");
    private static ConcreteNeeded yrds;
    private static CuFtPerUnit cuFtPerUnit = new CuFtPerUnit(27,0.60,0.45,0.30);
    //SqFtPerUnit Stores The CubicFt that covered by either
    //yards, 60lb, 40 lb, 80lb
    //The parameters that will be provided by the user
    private double width;
    private double rise;
    private double run;
    private double steps;
    private double platformD; // Platform Depth
    //Variables to Store the Calculated Values
    private float fPrice;
    private double Cubicft;
    private double Yards;
    private double b80;
    private double b60;
    private double b40;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stairs);
        //This shows the name, sub name, and icon image on the  Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Concrete Stairs Calculator");
        actionBar.setIcon(R.drawable.ic_subpic);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        _Do = (Button)findViewById(R.id.calcButton);
        _Do.setOnClickListener(this);

    }
    // This adds our custom tool bar settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // These are the navigation instructions for the toolbar selections
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(getApplicationContext(), Home.class));
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
    @Override
    public void onClick(View v){
        //Gets and assigns the inserted values from the interface to a variable
        EditText editWidth = findViewById(R.id.editWidth);
        EditText editRise = findViewById(R.id.editRise);
        EditText editRun = findViewById(R.id.editRun);
        EditText editSteps = findViewById(R.id.editSteps);
        EditText editPlatformD = findViewById(R.id.editPlatformD);
        //Converts the string values to Double's in order to use in calculations
        width = Double.parseDouble(editWidth.getText().toString());
        rise = Double.parseDouble((editRise.getText().toString()));
        run = Double.parseDouble((editRun.getText().toString()));
        steps = Double.parseDouble(editSteps.getText().toString());
        platformD = Double.parseDouble(editPlatformD.getText().toString());
        //Perform Conversion of inches to feet
        rise = rise/12;
        run = run /12;
        //Gets the Cubicft of the Stairs
        double StairVolume = getVUnderStairs();
        //Gets the Cubic ft of the Platform
        double PlatVolume = getVUnderPlat();
        //Adds the previously Calculated Cubic Ft
        // to get The total Cubic ft of the set of Stairs
        Cubicft = StairVolume + PlatVolume;
        //Calculates the Needed Concrete
        yrds = new ConcreteNeeded(cuFtPerUnit,Cubicft);
        Yards = yrds.getYrds();
        b80 = yrds.getd80();
        b60 = yrds.getd60();
        b40 = yrds.getd40();

        //Text View Boxes
        //Print Yards
        TextView txtYards = findViewById(R.id.txtYards);
        txtYards.setText("Yards: " + Double.toString(Double.parseDouble(df.format(Yards))));
        //Print 80 Lbs bags of Concrete needed
        TextView txt80 = findViewById(R.id.txtBags80);
        txt80.setText("80Lb Bags: " + Double.toString(Double.parseDouble(df.format(b80))));
        //Print 60 Lbs bags of concrete needed
        TextView txt60 = findViewById(R.id.txtBags60);
        txt60.setText("60Lb Bags: " + Double.toString(Double.parseDouble(df.format(b60))));
        //Print 40 lbs bas of concrete needed
        TextView txt40 = findViewById(R.id.txtBags40);
        txt40.setText("40Lb Bags: " + Double.toString((Double.parseDouble(df.format(b40)))));
    }

    //Method Gets the Volume Under the Platform
    private double getVUnderPlat(){
        double R;      // PlatformDepth * Rise * Width
        double platVolume;
        R = platformD * rise * width;
        platVolume = R * steps;         //Platform Vol = R * num of steps
        return platVolume;
    }
    //Method Calculates the Volume Under the Stairs
    private double getVUnderStairs() {
        int i;
        double StairVol;
        double TotalVol = 0;
        double R = rise * run * width;
        for (i = 1; i <= steps; i++) {        // iterates through each step to find its volume
            StairVol = i * R;
            TotalVol = TotalVol + StairVol;   // adds each step Vol to total
        }
        return TotalVol;
    }
}