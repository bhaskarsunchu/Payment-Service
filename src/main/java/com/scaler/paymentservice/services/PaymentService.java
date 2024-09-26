package com.scaler.paymentservice.services;

import com.razorpay.RazorpayException;
import com.scaler.paymentservice.paymentgateways.stripe.StripePaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public String createPaymentLink(Long amount, Long orderId, String email, String phoneNumber) {

        try {
            return paymentGatewayChooserStrategy
                    .getBestPaymentGateway()
                    .generatePaymentLink(amount, orderId, email, phoneNumber);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }

}
