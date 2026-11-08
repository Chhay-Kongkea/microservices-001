package kh.edu.istad.platform.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


public record InitiateOrderPaymentRequest(
        UUID orderId,
        UUID customerId,
        BigDecimal price
) {
    @Override
    public UUID orderId() {
        return orderId;
    }

    @Override
    public UUID customerId() {
        return customerId;
    }

    @Override
    public BigDecimal price() {
        return price;
    }
}