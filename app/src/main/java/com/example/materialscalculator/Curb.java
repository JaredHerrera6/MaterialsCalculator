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

public class Curb extends AppCompatActivity implements View.OnClickListener {
    private Button _Do;
    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat dfQuote =  new DecimalFormat("$###,###,##0.00");
    private static ConcreteNeeded yrds;
    private static CuFtPerUnit cuFtPerUnit = new CuFtPerUnit(27,0.60,0.45,0.30);
    //SqFtPerUnit Stores The CubicFt that covered by either
    //yards, 60lb, 40 lb, 80lb
    //The parameters that will be provided by the user
    private double Length;
    private double curbDepth;
    private double curbHeight;
    private double width;
    private double thickness;
    //Variables to Store teh Calculated Values
    private double Cubicft;
    private double Yards;
    private double b80;
    private double b60;
    private double b40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curb);
        //This shows the name, sub name, and icon image on the  Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Concrete Curb Calculator");
        actionBar.setIcon(R.drawable.ic_subpic);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        _Do = (Button)findViewById(R.id.calcButton);
        _Do.setOnClickListener(this);

    }// This adds our custom tool bar settings
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
        //Gets and assigns the inserted values from the user to a variable
        EditText editLength = findViewById(R.id.editLength);
        EditText editCurbDepth = findViewById(R.id.editCurbDepth);
        EditText editCurbHeight = findViewById(R.id.editCurbHeight);
        EditText editWidth = findViewById(R.id.editWidth);
        EditText editThickness = findViewById(R.id.editThickness);

        //Converts the String values to a Double in order to use in calculations
        Length = Double.parseDouble(editLength.getText().toString());
        curbDepth = Double.parseDouble(editCurbDepth.getText().toString());
        thickness = Double.parseDouble(editThickness.getText().toString());
        curbHeight = Double.parseDouble(editCurbHeight.getText().toString());
        width = Double.parseDouble(editWidth.getText().toString());

        //Perform conversion of inches to feet
        thickness = thickness/12;
        curbHeight = curbHeight/12;
        curbDepth = curbDepth/12;
        thickness = thickness/12;
        //Calculate teh total volume of the Curb
        double curbVolume = curbDepth * (curbHeight + thickness) * Length;
        //Calculate the total volume of the Gutter
        double gutterVolume = width * thickness * Length;
        //Add the both volumes to get the total volume/cubuc ft
        Cubicft = curbVolume + gutterVolume;
        //Calculates the Needed Concrete
        yrds = new ConcreteNeeded(cuFtPerUnit, Cubicft);
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
}