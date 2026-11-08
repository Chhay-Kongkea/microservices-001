package kh.edu.istad.platform.business.domain.dto;

public record RejectOrderResult(
        String businessId,
        String orderId,
        String approvalId,
        String status
) {
}
