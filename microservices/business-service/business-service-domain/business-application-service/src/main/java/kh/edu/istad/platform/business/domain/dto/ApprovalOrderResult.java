package kh.edu.istad.platform.business.domain.dto;

public record ApprovalOrderResult(
        String businessId,
        String orderId,
        String approvalId,
        String status
) {
}
