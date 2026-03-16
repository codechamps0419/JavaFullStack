package com.example.common;

// Shared DTO used across all modules — part of the common module
public class Order {
    private String id;
    private double amount;

    public Order(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Order[id=" + id + ", amount=$" + amount + "]";
    }
}
