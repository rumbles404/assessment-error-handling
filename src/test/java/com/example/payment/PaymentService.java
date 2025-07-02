package com.example.payment;

public class PaymentService {

    private final PaymentGateway gateway;

    public PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public boolean makePayment(String userId, double amount) {
        try {
            if (userId == null || userId.isEmpty()) {
                throw new Exception("Invalid user ID");
            }

            boolean result = gateway.charge(userId, amount);
            if (!result) {
                System.out.println("Payment failed for user: " + userId);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
