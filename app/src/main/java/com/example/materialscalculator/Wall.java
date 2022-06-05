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

public class Wall extends AppCompatActivity implements View.OnClickListener {
    private Button _Do;
    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat dfQuote =  new DecimalFormat("$###,###,##0.00");
    private static ConcreteNeeded yrds;
    private static CuFtPerUnit cuFtPerUnit = new CuFtPerUnit(27,0.60,0.45,0.30);
    //SqFtPerUnit Stores The CubicFt that covered by either
    //yards, 60lb, 40 lb, 80lb
    //The parameters that will be provided by the user
    private double Length;
    private double Thickness;
    private double Height;
    //Variables to Store the Calculated Values
    private double Cubicft;
    private double Yards;
    private double b80;
    private double b60;
    private double b40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        //This shows the name, sub name, and icon image on the  Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Concrete Wall Calculator");
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
        //Gets and assigns the inserted values from the user to a variable
        EditText editLength = findViewById(R.id.editLength);
        EditText editHeight = findViewById(R.id.editHeight);
        EditText editThickness = findViewById(R.id.editThickness);

        //Converts the String values to a Doubles in order to use in calculations
        Length = Double.parseDouble(editLength.getText().toString());
        Height = Double.parseDouble(editHeight.getText().toString());
        Thickness = Double.parseDouble(editThickness.getText().toString());
        //Perform Conversion of inches to feet
        Thickness = Thickness/12;
        //Get the Cubic Feet of the Wall
        Cubicft = Length * Height * Thickness;
        //Calculates The needed Concrete
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