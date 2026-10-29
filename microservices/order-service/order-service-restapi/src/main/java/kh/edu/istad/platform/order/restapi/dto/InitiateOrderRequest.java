package kh.edu.istad.platform.order.restapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kh.edu.istad.platform.order.domain.entity.OrderItem;
import kh.edu.istad.platform.order.domain.valueobject.StreetAddress;

import java.util.List;
import java.util.UUID;

public record InitiateOrderRequest(
        @NotNull(message = "Customer ID is required")
        UUID customerId,

        @NotNull(message = "Business ID is required")
        UUID businessId,

        @NotNull(message = "Street address is required")
        @Valid
        StreetAddress streetAddress,

        @NotEmpty(message = "Order must contain at least one item")
        List<@NotNull(message = "Order item cannot be null") @Valid OrderItemRequest> items
) {
}
