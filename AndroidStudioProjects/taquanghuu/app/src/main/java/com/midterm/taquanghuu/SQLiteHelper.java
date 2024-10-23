package com.midterm.taquanghuu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OrderDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Orders";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_WHIPPED_CREAM = "whipped_cream";
    private static final String COLUMN_CHOCOLATE = "chocolate";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STATUS = "status";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_WHIPPED_CREAM + " INTEGER, " +
                COLUMN_CHOCOLATE + " INTEGER, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_STATUS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Add a new order
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, order.getQuantity());
        values.put(COLUMN_WHIPPED_CREAM, order.isAddWhippedCream() ? 1 : 0);
        values.put(COLUMN_CHOCOLATE, order.isAddChocolate() ? 1 : 0);
        values.put(COLUMN_PRICE, order.getPrice());
        values.put(COLUMN_STATUS, order.getStatus());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Retrieve all orders
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(
                        cursor.getInt(1), // quantity
                        cursor.getInt(2) == 1, // whipped_cream
                        cursor.getInt(3) == 1, // chocolate
                        cursor.getDouble(4), // price
                        cursor.getString(5) // status
                );
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;
    }

    // Retrieve orders by price
    public ArrayList<Order> getOrdersByPrice(double price) {
        ArrayList<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRICE + "=?", new String[]{String.valueOf(price)});

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(
                        cursor.getInt(1), // quantity
                        cursor.getInt(2) == 1, // whipped_cream
                        cursor.getInt(3) == 1, // chocolate
                        cursor.getDouble(4), // price
                        cursor.getString(5) // status
                );
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;
    }
}
