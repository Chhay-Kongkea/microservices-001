package kh.edu.istad.platform.order.domain.dto;

import java.util.UUID;

public record CancelOrderCommand(
        UUID orderId,
        UUID customerId
) {}