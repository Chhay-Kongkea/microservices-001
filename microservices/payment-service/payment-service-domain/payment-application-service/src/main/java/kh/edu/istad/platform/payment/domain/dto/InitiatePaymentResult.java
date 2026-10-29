package kh.edu.istad.platform.payment.domain.dto;

import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public record InitiatePaymentResult(
        UUID orderId,
        UUID customerId,
        Money price,
        PaymentStatus paymentStatus,
        ZonedDateTime createdAt
) {
}
