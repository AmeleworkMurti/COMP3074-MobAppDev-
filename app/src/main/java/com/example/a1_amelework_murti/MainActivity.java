package com.example.a1_amelework_murti;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    EditText etHours, etRate;
    Button btnCalculate;
    TextView tvPay, tvOvertime, tvTotal, tvTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvPay = findViewById(R.id.tvPay);
        tvOvertime = findViewById(R.id.tvOvertime);
        tvTotal = findViewById(R.id.tvTotal);
        tvTax = findViewById(R.id.tvTax);

        //here adding button click listener
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePayment();
            }
        });
    }

    private void calculatePayment() {
        String hoursStr = etHours.getText().toString().trim();
        String rateStr = etRate.getText().toString().trim();

        // this will validate input
        if (TextUtils.isEmpty(hoursStr) || TextUtils.isEmpty(rateStr)) {
            Toast.makeText(this, "Please enter both hours and rate", Toast.LENGTH_SHORT).show();
            return;
        }

        double hours = Double.parseDouble(hoursStr);
        double rate = Double.parseDouble(rateStr);

        double pay;
        double overtimePay = 0;

        if (hours <= 40) {
            pay = hours * rate;
        } else {
            overtimePay = (hours - 40) * rate * 1.5;
            pay = (40 * rate) + overtimePay;
        }

        double tax = pay * 0.18;

        // Display results
        tvPay.setText("Pay: " + (hours <= 40 ? (hours * rate) : (40 * rate)));
        tvOvertime.setText("Overtime Pay: " + overtimePay);
        tvTotal.setText("Total Pay: " + pay);
        tvTax.setText("Tax: " + tax);

        Toast.makeText(this, "Calculation Successful!", Toast.LENGTH_SHORT).show();
    }
}