package com.example.de1_2021;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etInput, etAction;
    private TextView tvOutput;
    private List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        etAction = findViewById(R.id.etAction);
        tvOutput = findViewById(R.id.tvOutput);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnHistory = findViewById(R.id.btnHistory);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString().trim();
                String action = etAction.getText().toString().trim();

                if (input.isEmpty() || action.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter input and action", Toast.LENGTH_SHORT).show();
                } else {
                    if (action.equals("remove-even")) {
                        String result = removeEvenNumbers(input);
                        tvOutput.setText(result);
                        historyList.add("Input: " + input + ", Action: " + action + ", Output: " + result);
                    } else if (action.equals("count-letter-digit")) {
                        String result = countLettersAndDigits(input);
                        tvOutput.setText(result);
                        historyList.add("Input: " + input + ", Action: " + action + ", Output: " + result);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid action", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putStringArrayListExtra("history", (ArrayList<String>) historyList);
                startActivity(intent);
            }
        });
    }

    private String removeEvenNumbers(String input) {
        String[] numbers = input.split(",");
        StringBuilder result = new StringBuilder();
        for (String number : numbers) {
            int num = Integer.parseInt(number.trim());
            if (num % 2 != 0) {
                result.append(num).append(",");
            }
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }

    private String countLettersAndDigits(String input) {
        int letters = 0, digits = 0;
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                letters++;
            } else if (Character.isDigit(c)) {
                digits++;
            }
        }
        return "LETTERS: " + letters + ", DIGITS: " + digits;
    }
}
