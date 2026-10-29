package kh.edu.istad.platform.order.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record InitiateOrderItemCommand(
        UUID productId,
        BigDecimal price,
        Integer quantity
) {
}