package kh.edu.istad.platform.payment.domain.dto;

import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

public record UpdatePaymentCommand(
        OrderId orderId,
        PaymentStatus paymentStatus
) {
}
