package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnSub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvCount = findViewById(R.id.tv_count);
        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);

        // Method 1: Using OnClickListener
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                tvCount.setText("" + ++count);
//            }
//        });
//
//        btnSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                tvCount.setText("" + --count);
//            }
//        });
    }

    // Method 2: Using android:onclick
    public void incrementCount(View view) {
        int count = Integer.parseInt(tvCount.getText().toString());
        tvCount.setText(String.valueOf(++count));
    }
    public void decrementCount(View view) {
        int count = Integer.parseInt(tvCount.getText().toString());
        tvCount.setText(String.valueOf(--count));
    }

}