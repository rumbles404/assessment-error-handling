package com.example.payment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentServiceTest {

    @Test
    public void testSuccessfulPayment() {
        PaymentGateway gateway = (userId, amount) -> true;
        PaymentService service = new PaymentService(gateway);
        Assert.assertTrue(service.makePayment("user123", 100.0));
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testInvalidUserId() {
        PaymentGateway gateway = (userId, amount) -> true;
        PaymentService service = new PaymentService(gateway);
        Assert.assertFalse(service.makePayment("", 100.0));
    }

    @Test
    public void testGatewayFailure() {
        PaymentGateway gateway = (userId, amount) -> false;
        PaymentService service = new PaymentService(gateway);
        Assert.assertFalse(service.makePayment("user123", 100.0));
    }
}