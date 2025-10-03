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


// Static list to store all payment records
public static java.util.ArrayList<String> paymentHistory = new java.util.ArrayList<>();


public class MainActivity extends AppCompatActivity {


    EditText etHours, etRate;
    Button btnCalculate;
    TextView tvPay, tvOvertime, tvTotal, tvTax;

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.action_details) {
            //bottom line opens DetailActivity
            android.content.Intent intent = new android.content.Intent(this, DetailActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


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

        // Validate input
        if (hours <= 0 || rate <= 0) {
            android.widget.Toast.makeText(this,
                    "Error: Please enter valid positive numbers!",
                    android.widget.Toast.LENGTH_LONG).show();
            return; // stop calculation
        }

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

        //Toast.makeText(this, "Calculation Successful!", Toast.LENGTH_SHORT).show();


        // Create a string record of the calculation
        String record = "Hours: " + hours +
                ", Rate: " + rate +
                ", Total Pay: " + pay +
                ", Tax: " + tax;

        // Add record to static list
        paymentHistory.add(record);

        android.widget.Toast.makeText(this,
                "Payment calculation successful!",
                android.widget.Toast.LENGTH_SHORT).show();

    }
}