package com.midterm.taquanghuu;

public class Order {

    private int quantity;
    private boolean addWhippedCream;
    private boolean addChocolate;
    private double price;
    private String status;

    public Order(int quantity, boolean addWhippedCream, boolean addChocolate, double price, String status) {
        this.quantity = quantity;
        this.addWhippedCream = addWhippedCream;
        this.addChocolate = addChocolate;
        this.price = price;
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAddWhippedCream() {
        return addWhippedCream;
    }

    public boolean isAddChocolate() {
        return addChocolate;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
