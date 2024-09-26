package com.scaler.paymentservice.controllers;

import com.scaler.paymentservice.dtos.CreatePaymentLinkRequestDTO;
import com.scaler.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/link")
    public ResponseEntity<String> createPaymentLink(@RequestBody CreatePaymentLinkRequestDTO request) {
        try {
            String paymentLink = paymentService.createPaymentLink(request.getAmount(), request.getOrderId(),
                    request.getEmail(), request.getPhoneNumber());
            return ResponseEntity.ok(paymentLink);
        } catch (Exception e) {
            // Handle Stripe exceptions gracefully, e.g., return appropriate error response with HTTP status code
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment link: " + e.getMessage());
        }
    }
}
