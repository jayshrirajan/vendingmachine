package com.example.vmApp.model;

public class vendItemRequest {
    private int itemId;
    private double amount;

    public int getItemId() {

        return itemId;
    }

    public void setItemId(int itemId) {

        this.itemId = itemId;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    private int quantity;
}
