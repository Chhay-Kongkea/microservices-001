package kh.edu.istad.platform.order.domain.dto;

import kh.edu.istad.platform.order.domain.entity.OrderItem;
import kh.edu.istad.platform.order.domain.valueobject.StreetAddress;

import java.util.List;
import java.util.UUID;

public record InitiateOrderCommand(
        UUID customerId,
        UUID businessId,
        StreetAddress streetAddress,
        List<InitiateOrderItemCommand> items
) {
}
