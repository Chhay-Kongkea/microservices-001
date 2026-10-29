package kh.edu.istad.platform.order.domain.dto;

import kh.edu.istad.common.domain.valueobject.OrderStatus;
import kh.edu.istad.platform.order.domain.valueobject.TrackingId;

import java.util.UUID;

public record CancelOrderResult(
        UUID orderId,
        TrackingId trackingId,
        OrderStatus orderStatus,
        String message
) {
}
