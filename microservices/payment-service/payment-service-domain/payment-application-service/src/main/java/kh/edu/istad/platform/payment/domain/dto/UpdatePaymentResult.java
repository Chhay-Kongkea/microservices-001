package kh.edu.istad.platform.payment.domain.dto;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;

public record UpdatePaymentResult(
        OrderId orderId,
        CustomerId customerId,
        Money price,
        PaymentStatus paymentStatus,
        ZonedDateTime createdAt
) {
}
