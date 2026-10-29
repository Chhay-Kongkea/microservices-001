package kh.edu.istad.platform.payment.restapi.dto;

import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public record InitiatePaymentResponse(
        UUID orderId,
        UUID customerId,
        Money price,
        PaymentStatus paymentStatus,
        ZonedDateTime createdAt
) {
}
