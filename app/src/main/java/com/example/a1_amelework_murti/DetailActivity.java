package com.example.a1_amelework_murti2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_amelework_murti.MainActivity;
import com.example.a1_amelework_murti.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Enable back button in ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Find ListView
        ListView listView = findViewById(R.id.paymentListView);

        // Bind paymentHistory from MainActivity
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.paymentHistory
        );

        listView.setAdapter(adapter);
    }

    // Handle back button in ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // closes this activity and goes back
        return true;
    }
}
