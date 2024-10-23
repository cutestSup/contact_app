package com.midterm.contactappv1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.midterm.contactappv1.databinding.ActivityMainBinding;
import com.midterm.contactappv1.databinding.ActivityNewContactBinding;

public class NewContactActivity extends AppCompatActivity {

    private ActivityNewContactBinding binding;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                validateInput();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        binding.edTName.addTextChangedListener(textWatcher);
        binding.edTPhone.addTextChangedListener(textWatcher);
        binding.edTEmail.addTextChangedListener(textWatcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Nạp menu từ tệp XML
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent returnIntent = new Intent();

        if (id == R.id.btn_back) {
            setResult(RESULT_CANCELED,returnIntent);
            finish();
        }

        if (id == R.id.btn_save) {
            returnIntent.putExtra("name",binding.edTName.getText().toString());
            returnIntent.putExtra("phone",binding.edTPhone.getText().toString());
            returnIntent.putExtra("email",binding.edTEmail.getText().toString());
            setResult(RESULT_OK,returnIntent);
            finish();
        }
        return true;
    }

    private void validateInput() {
        String input1 = binding.edTName.getText().toString().trim();
        String input2 = binding.edTPhone.getText().toString().trim();
        String input3 = binding.edTEmail.getText().toString().trim();

        if (!input1.isEmpty() && !input2.isEmpty() && !input3.isEmpty()) {
            menu.findItem(R.id.btn_save).setEnabled(true);
        } else {
            menu.findItem(R.id.btn_save).setEnabled(false); // Tắt nút
        }
    }


}