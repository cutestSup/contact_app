package com.midterm.androidplayaround;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.midterm.androidplayaround.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MyViewModel model;

    private ArrayList<Integer> arrayList;
    private ArrayAdapter<Integer> arrayAdapter;

    private static final int DETAILS_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,model.getItems().getValue());
        binding.lvCount.setAdapter(arrayAdapter);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText(""+integer);

            }
        });

        model.getItems().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> items) {
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.increaseNumber();
                model.addItem(model.getNumber().getValue());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.decreaseNumber();
                model.addItem(model.getNumber().getValue());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                model.removeItem(model.getItems().getValue().get(i));
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent.putExtra("number", model.getItems().getValue().get(i).toString());
//                intent.putExtra("id",i);
                startActivityForResult(intent, DETAILS_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DETAILS_ACTIVITY_REQUEST_CODE) {
            String returnString = null;
            Integer id=0;
            if (resultCode == RESULT_OK) {
                returnString = data.getStringExtra("result");
                id = data.getIntExtra("result1",0);
            }
            model.getItems().getValue().set(id, Integer.parseInt(returnString));
            arrayAdapter.notifyDataSetChanged();
        }
    }
}