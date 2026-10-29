package kh.edu.istad.platform.business.domain.dto;

public record ApprovedOrderResult(
        String businessId,
        String orderId,
        String approvalId,
        String status
) {
}
