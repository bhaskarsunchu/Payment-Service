package com.scaler.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestDTO {
    private Long orderId;
    private String email;
    private String phoneNumber;
    private Long amount;
}
