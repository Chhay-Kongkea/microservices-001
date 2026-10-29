package kh.edu.istad.platform.order.restapi.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PayOrderRequest(
        @NotNull(message = "Order ID is required")
        UUID orderId,

        @NotNull(message = "Customer ID is required")
        UUID customerId

) {}