package kh.edu.istad.platform.order.domain.dto;

public record PayOrderResult(
        String paymentId,
        String orderId,
        boolean successful,
        String message
) {
}
