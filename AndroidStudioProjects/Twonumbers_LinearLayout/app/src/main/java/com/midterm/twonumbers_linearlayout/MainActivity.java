package com.midterm.twonumbers_linearlayout;

import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.midterm.twonumbers_linearlayout.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    private MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        arrayList = new ArrayList<String >();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,model.getItems().getValue());
        binding.lvHis.setAdapter(arrayAdapter);

        model.getItems().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edTA.getText().toString().isEmpty() && !binding.edTB.getText().toString().isEmpty()) {
                    float a = Float.parseFloat(binding.edTA.getText().toString());
                    float b = Float.parseFloat(binding.edTB.getText().toString());
                    float res = a + b;
                    String tmp = a + " + " + b + " = " + res;
                    model.addItem(tmp);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edTA.getText().toString().isEmpty() && !binding.edTB.getText().toString().isEmpty()) {
                    float a = Float.parseFloat(binding.edTA.getText().toString());
                    float b = Float.parseFloat(binding.edTB.getText().toString());
                    float res = a - b;
                    String tmp = a + " - " + b + " = " + res;
                    model.addItem(tmp);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edTA.getText().toString().isEmpty() && !binding.edTB.getText().toString().isEmpty()) {
                    float a = Float.parseFloat(binding.edTA.getText().toString());
                    float b = Float.parseFloat(binding.edTB.getText().toString());
                    float res = a * b;
                    String tmp = a + " * " + b + " = " + res;
                    model.addItem(tmp);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        binding.btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edTA.getText().toString().isEmpty() && !binding.edTB.getText().toString().isEmpty()) {
                    float a = Float.parseFloat(binding.edTA.getText().toString());
                    float b = Float.parseFloat(binding.edTB.getText().toString());
                    String tmp = "";
                    if (b == 0) {
                        tmp = "Can not divide 0!";
                    } else {
                        float res = a / b;
                        tmp = a + " / " + b + " = " + res;
                    }
                    model.addItem(tmp);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });



    }
}