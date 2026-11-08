package kh.edu.istad.platform.payment.restapi.dto;

import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

public record UpdatePaymentRequest(
        OrderId orderId,
        PaymentStatus paymentStatus
) {
}
