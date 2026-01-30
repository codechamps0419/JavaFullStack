package com.example.payment;

/*
 *  @author CodeChamps0419
 */

// Entry point — run with: java --module-path mods -m com.example.payment/com.example.payment.Main
public class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.processPayment("ORD-001", 199.99); // success scenario
        paymentService.processPayment("ORD-002", 49.50);  // another order
    }
}
