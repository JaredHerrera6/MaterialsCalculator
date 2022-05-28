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
import android.widget.Toast;

import java.text.DecimalFormat;

public class Slab extends AppCompatActivity implements View.OnClickListener {
    //Declare the button name to _Do
    private Button _Do;

    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat dfQuote =  new DecimalFormat("$###,###,##0.00");
    private static ConcreteNeeded yrds;
    private static CuFtPerUnit cuFtPerUnit = new CuFtPerUnit(27,0.60,0.45,0.30);
    // SqFtPerUnit Stores the Cubicft that covered by either
    // yards, 60 lbs bag, or 80 lbs bag
    private static CubicFeet cubicFeet;
    private static Estimate estimate;
    private double Length;
    private double width;
    private double thickness;
    private float fPrice;
    private double Cubicft;
    private double Yards;
    private double sqft;
    private double b80;
    private double b60;
    private double b40;
    private double dQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slab);
        //This shows the name, sub name, and icon image on the  Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jared's Jobs");
        actionBar.setSubtitle("Concrete Slab Calculator");
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
    public void onClick(View v) {
        EditText editLength = findViewById(R.id.editLength);
        EditText editWidth = findViewById(R.id.editWidth);
        EditText editThick = findViewById(R.id.editThickness);
        EditText editPrice = findViewById(R.id.editPrice);
        Length = Double.parseDouble(editLength.getText().toString());
        width = Double.parseDouble(editWidth.getText().toString());
        thickness = Double.parseDouble(editThick.getText().toString());
        fPrice = Integer.parseInt(editPrice.getText().toString());

        thickness = thickness/ 12; // turns thickness(inches) into feet by dividing by 12
        cubicFeet = new CubicFeet(Length,width, thickness);
        Cubicft = cubicFeet.getCubicft();

        yrds = new ConcreteNeeded(cuFtPerUnit,Cubicft);
        Yards = yrds.getYrds();
        b80 = yrds.getd80();
        b60 = yrds.getd60();
        b40 = yrds.getd40();

        sqft = cubicFeet.getSqft();
        estimate = new Estimate(sqft,fPrice);
        dQuote = estimate.getEstimate();

        // Text view boxes
        // Print yards
        TextView txtYards = findViewById(R.id.txtYards);
        txtYards.setText("Yards: " + Double.toString(Double.parseDouble(df.format(Yards))));
        //Print 80 Lbs bags of concrete needed
        TextView txt80 = findViewById(R.id.txtBags80);
        txt80.setText("80Lb Bags: " + Double.toString(Double.parseDouble(df.format(b80))));
        //Print 60 Lbs bags of concrete needed
        TextView txt60 = findViewById(R.id.txtBags60);
        txt60.setText("60Lb Bags: " + Double.toString(Double.parseDouble(df.format(b60))));
        //Print 40 lbs bas of concrete needed
        TextView txt40 = findViewById(R.id.txtBags40);
        txt40.setText("40Lb Bags: " + Double.toString((Double.parseDouble(df.format(b40)))));
        //Print Estimate
        TextView txtEstimate = findViewById(R.id.txtEstimate);
        txtEstimate.setText("Estimate: " + dfQuote.format(dQuote));
    }
}