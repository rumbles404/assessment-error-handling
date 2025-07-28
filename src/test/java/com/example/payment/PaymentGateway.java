package com.example.payment;

public interface PaymentGateway {
    boolean charge(String userId, double amount) throws Exception;
}