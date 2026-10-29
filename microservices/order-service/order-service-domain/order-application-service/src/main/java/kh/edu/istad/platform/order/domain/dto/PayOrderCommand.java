package kh.edu.istad.platform.order.domain.dto;

import java.util.UUID;

public record PayOrderCommand(
        UUID orderId,
        UUID customerId
) {}