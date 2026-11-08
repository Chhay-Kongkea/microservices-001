package kh.edu.istad.platform.payment.domain.dto;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


public record InitiatePaymentCommand(
        UUID orderId,
        UUID customerId,
        BigDecimal price
) {


    public InitiatePaymentCommand(UUID orderId, UUID customerId, BigDecimal price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
    }
}
