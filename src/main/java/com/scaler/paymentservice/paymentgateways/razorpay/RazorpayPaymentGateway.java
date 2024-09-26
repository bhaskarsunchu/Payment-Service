package com.scaler.paymentservice.paymentgateways.razorpay;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.scaler.paymentservice.paymentgateways.PaymentGateway;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {

    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long amount, Long orderId, String email, String phoneNumber) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();

        // Set amount, currency, and other details
        paymentLinkRequest.put("amount", amount * 100);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("accept_partial", false);

        // Calculate expire_by time (20 minutes from now)
        long expireBy = Instant.now().getEpochSecond() + 1200;  // 1200 seconds = 20 minutes
        paymentLinkRequest.put("expire_by", expireBy);

        // Set reference ID and description
        paymentLinkRequest.put("reference_id", "" + orderId);
        paymentLinkRequest.put("description", "Payment for order #" + orderId);

        // Customer details
        JSONObject customer = new JSONObject();
        customer.put("name", "Jagan Baskar");
        // Updated valid contact number (must be between 8 and 14 digits)
        customer.put("contact", "9876543210");
        customer.put("email", "email@gmail.com");
        paymentLinkRequest.put("customer", customer);

        // Notification settings
        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        // Other settings
        paymentLinkRequest.put("reminder_enable", true);
        paymentLinkRequest.put("callback_url", "https://www.scaler.com");
        paymentLinkRequest.put("callback_method", "get");

        // Create payment link
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        // Return the short URL of the payment link
        return payment.get("short_url").toString();

    }
}
