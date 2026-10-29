package kh.edu.istad.platform.order.restapi.dto;

import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderStatus;
import kh.edu.istad.platform.order.domain.valueobject.TrackingId;

import java.util.UUID;

public record InitiateOrderResponse(
        UUID orderId,
        UUID customerId,
        UUID businessId,
        TrackingId trackingId,
        OrderStatus orderStatus,
        Money price,
        String message
) {
}
