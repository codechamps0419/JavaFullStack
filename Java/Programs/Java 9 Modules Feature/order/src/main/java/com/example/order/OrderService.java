package com.example.order;

import com.example.common.Order; // Order is from common module — accessible because module-info requires transitive common

// Creates Order objects — part of the order module
public class OrderService {

    public Order createOrder(String id, double amount) {
        System.out.println("[OrderService] Creating order: " + id + " for $" + amount);
        return new Order(id, amount);
    }
}
