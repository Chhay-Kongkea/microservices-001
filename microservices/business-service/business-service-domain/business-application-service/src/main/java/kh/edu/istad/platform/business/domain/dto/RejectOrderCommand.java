package kh.edu.istad.platform.business.domain.dto;

public record RejectOrderCommand(
        String businessId,
        String orderId,
        String reason
) {
}
