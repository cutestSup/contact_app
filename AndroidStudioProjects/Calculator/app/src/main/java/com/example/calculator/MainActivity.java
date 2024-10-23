package com.example.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Calculation solve = new Calculation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvInput.setText("");
                binding.tvResult.setText("");
            }
        });
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                binding.tvInput.append(buttonText);
            }
        };

        binding.btn0.setOnClickListener(numberClickListener);
        binding.btn1.setOnClickListener(numberClickListener);
        binding.btn2.setOnClickListener(numberClickListener);
        binding.btn3.setOnClickListener(numberClickListener);
        binding.btn4.setOnClickListener(numberClickListener);
        binding.btn5.setOnClickListener(numberClickListener);
        binding.btn6.setOnClickListener(numberClickListener);
        binding.btn7.setOnClickListener(numberClickListener);
        binding.btn8.setOnClickListener(numberClickListener);
        binding.btn9.setOnClickListener(numberClickListener);
        binding.btnAdd.setOnClickListener(numberClickListener);
        binding.btnSub.setOnClickListener(numberClickListener);
        binding.btnMul.setOnClickListener(numberClickListener);
        binding.btnDiv.setOnClickListener(numberClickListener);
        binding.btnDot.setOnClickListener(numberClickListener);
        binding.btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = binding.tvInput.getText().toString();
                double x = solve.eval(input);
                String output = "";
                if(x-Math.round(x) == 0) {
                    Integer y = (int) x;
                    output = Integer.toString(y);
                } else {
                    output = Double.toString(x);
                }
                binding.tvResult.setText(input + " = " + output);
                binding.tvInput.setText(output);
            }
        });
    }
}
