package com.midterm.taquanghuu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> orderList;
    private EditText etPriceSearch;
    private Button btnSearch, btnAddOrder;

    private SQLiteHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerView = findViewById(R.id.orderRecyclerView);
        etPriceSearch = findViewById(R.id.etPriceSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnAddOrder = findViewById(R.id.fabAddOrder);

        // Initialize database helper
        dbHelper = new SQLiteHelper(this);

        // Load orders from database
        orderList = dbHelper.getAllOrders();

        // Set up RecyclerView with the adapter
        orderAdapter = new OrderAdapter(orderList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);

        // Search by price
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceInput = etPriceSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(priceInput)) {
                    double priceToSearch = Double.parseDouble(priceInput);
                    filterOrdersByPrice(priceToSearch);
                }
            }
        });

        // Add new order
        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to filter orders by price
    private void filterOrdersByPrice(double price) {
        ArrayList<Order> filteredList = dbHelper.getOrdersByPrice(price);
        orderAdapter.updateOrderList(filteredList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload orders after returning from OrderActivity
        orderList = dbHelper.getAllOrders();
        orderAdapter.updateOrderList(orderList);
    }
}
