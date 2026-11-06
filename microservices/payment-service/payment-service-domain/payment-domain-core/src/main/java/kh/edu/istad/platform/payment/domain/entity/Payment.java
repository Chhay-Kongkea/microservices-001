package kh.edu.istad.platform.payment.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public record Payment(
        String paymentId,
        String orderId,
        String customerId,
        BigDecimal amount,
        PaymentStatus status
) {

    public static Payment completed(String orderId, String customerId, BigDecimal amount) {
        return new Payment(
                UUID.randomUUID().toString(),
                orderId,
                customerId,
                amount,
                PaymentStatus.COMPLETED
        );
    }
}
