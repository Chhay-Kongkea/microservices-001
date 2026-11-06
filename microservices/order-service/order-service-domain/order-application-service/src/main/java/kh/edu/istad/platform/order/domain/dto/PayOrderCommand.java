package kh.edu.istad.platform.order.domain.dto;

public record PayOrderCommand(
        String orderId,
        String customerId,
        String price
) {
}
