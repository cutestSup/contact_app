package com.midterm.androidplayaround;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.midterm.androidplayaround.databinding.ActivityDetailsBinding;


public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    private MyViewModel model;

    private int id;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvDetail.setText(""+data);
            }
        });

        Intent receivedIntent = getIntent();
        if(receivedIntent != null) {
            data = receivedIntent.getStringExtra("number");
            id = receivedIntent.getIntExtra("id",0);
            binding.tvDetail.setText(data);
        }

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",binding.tvDetail.getText().toString());
                returnIntent.putExtra("result1", id);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
    }


}