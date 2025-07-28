package com.example.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentGateway gateway;

    public PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public boolean makePayment(String userId, double amount) {
        validateUserId(userId);
        validateAmount(amount);

        try {
            boolean result = gateway.charge(userId, amount);
            if (!result) {
                logger.warn("Payment failed for user: {}", userId);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception occurred during payment for user {}: {}", userId, e.getMessage(), e);
            return false;
        }
    }

    private void validateUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}