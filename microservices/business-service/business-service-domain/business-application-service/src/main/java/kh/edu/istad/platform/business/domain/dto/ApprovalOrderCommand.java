package kh.edu.istad.platform.business.domain.dto;

public record ApprovalOrderCommand(
        String businessId,
        String orderId
) {
}
