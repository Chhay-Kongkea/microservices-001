package kh.edu.istad.platform.order.restapi.dto;

import kh.edu.istad.common.domain.valueobject.OrderStatus;
import kh.edu.istad.platform.order.domain.valueobject.TrackingId;

import java.util.UUID;

public record CancelOrderResponse(
        UUID orderId,
        TrackingId trackingId,
        OrderStatus orderStatus,
        String message
) {
}
