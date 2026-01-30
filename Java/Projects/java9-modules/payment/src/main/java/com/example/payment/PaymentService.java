package com.example.payment;

import com.example.common.Order;       // accessible via transitive dependency from order module
import com.example.order.OrderService; // directly required in module-info

// Processes payments — part of the payment module
public class PaymentService {

    private OrderService orderService = new OrderService();

    public void processPayment(String orderId, double amount) {
        Order order = orderService.createOrder(orderId, amount);
        // Order class works here even though payment module never explicitly required common
        System.out.println("[PaymentService] Processing payment for: " + order);
        System.out.println("[PaymentService] Payment of $" + order.getAmount() + " completed!");
    }
}
