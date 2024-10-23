package com.midterm.taquanghuu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private EditText etQuantity;
    private CheckBox cbWhippedCream, cbChocolate;
    private TextView tvOrderSummary;
    private Button btnOrder;
    private SQLiteHelper dbHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize views
        etQuantity = findViewById(R.id.quantityTextView);
        cbWhippedCream = findViewById(R.id.creamCheckBox);
        cbChocolate = findViewById(R.id.chocolateCheckBox);
        tvOrderSummary = findViewById(R.id.orderSummaryTextView);
        btnOrder = findViewById(R.id.orderButton);

        // Initialize database helper
        dbHelper = new SQLiteHelper(this);

        // Order button functionality
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(etQuantity.getText().toString());
                boolean addWhippedCream = cbWhippedCream.isChecked();
                boolean addChocolate = cbChocolate.isChecked();

                double price = calculatePrice(quantity, addWhippedCream, addChocolate);
                String status = "Đang chế biến"; // Default status

                // Save order to database
                Order newOrder = new Order(quantity, addWhippedCream, addChocolate, price, status);
                dbHelper.addOrder(newOrder);

                // Display summary
                tvOrderSummary.setText("Order Summary:\n" +
                        "Quantity: " + quantity + "\n" +
                        "Whipped Cream: " + (addWhippedCream ? "Yes" : "No") + "\n" +
                        "Chocolate: " + (addChocolate ? "Yes" : "No") + "\n" +
                        "Price: $" + price);
            }
        });
    }

    private double calculatePrice(int quantity, boolean addWhippedCream, boolean addChocolate) {
        double basePrice = 4.00;
        if (addWhippedCream) basePrice += 0.50;
        if (addChocolate) basePrice += 1.00;
        return basePrice * quantity;
    }
}
