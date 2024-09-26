package com.scaler.paymentservice.services;

import com.scaler.paymentservice.paymentgateways.PaymentGateway;
import com.scaler.paymentservice.paymentgateways.razorpay.RazorpayPaymentGateway;
import com.scaler.paymentservice.paymentgateways.stripe.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayChooserStrategy {

    private StripePaymentGateway stripePaymentGateway;

    private RazorpayPaymentGateway razorpayPaymentGateway;

    public PaymentGatewayChooserStrategy(StripePaymentGateway stripePaymentGateway,
                                         RazorpayPaymentGateway razorpayPaymentGateway){
        this.stripePaymentGateway = stripePaymentGateway;
        this.razorpayPaymentGateway = razorpayPaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway(){
        // Implement the logic to choose the best PaymentGateway and return
        return stripePaymentGateway;
    }
}
