package kh.edu.istad.platform.order.restapi.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemRequest(
        @NotNull UUID productId,
        @NotNull BigDecimal price,
        @NotNull Integer quantity
) {}