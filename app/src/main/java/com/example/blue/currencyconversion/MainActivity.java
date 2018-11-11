package com.example.blue.currencyconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    double amountDouble = 0;
    double euroConversionRate = .88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final EditText amount = findViewById(R.id.editText);
        final RadioButton dToE = findViewById(R.id.radDolToEU);
        final RadioButton eToD = findViewById(R.id.radEUtoDol);
        Button convert = findViewById(R.id.convertButton);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView resultText = findViewById(R.id.resultText);
                String amountString = amount.getText().toString();
                double result = 0;
                try{
                    amountDouble = Double.parseDouble(amountString);
                }catch (NumberFormatException d){
                    amountDouble = 0;
                }
                if(dToE.isChecked()){
                    result = amountDouble * euroConversionRate;
                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.GERMANY);
                    String resultString = format.format(result);
                    resultText.setText(resultString);
                    if(amountDouble > 10000){
                        Toast.makeText(MainActivity.this, "Wow! That's a lot of money", Toast.LENGTH_LONG ).show();
                    }
                }
                else if(eToD.isChecked()){
                    result = amountDouble / euroConversionRate;
                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                    String resultString = format.format(result);
                    resultText.setText(resultString);
                }else {
                    Toast.makeText(MainActivity.this, "Please choose one of the exchanges",Toast.LENGTH_LONG ).show();
                }
            }
        });

    }
}
