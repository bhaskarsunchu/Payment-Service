package com.scaler.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {

    public String generatePaymentLink(Long amount, Long orderId, String email, String phoneNumber) throws StripeException, RazorpayException;
}
