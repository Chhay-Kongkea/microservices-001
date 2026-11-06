package kh.edu.istad.platform.payment.domain.usecase;

import kh.edu.istad.platform.payment.domain.entity.Payment;

import java.math.BigDecimal;

public interface PayOrderUseCase {

    Payment payOrder(String orderId, String customerId, BigDecimal amount);
}
