package kh.edu.istad.platform.payment.domain.service;

import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.usecase.PayOrderUseCase;

import java.math.BigDecimal;

public class PayOrderService implements PayOrderUseCase {

    @Override
    public Payment payOrder(String orderId, String customerId, BigDecimal amount) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID is required");
        }
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID is required");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Demo only: simulate a successful payment. No real payment provider is called.
        return Payment.completed(orderId, customerId, amount);
    }
}
