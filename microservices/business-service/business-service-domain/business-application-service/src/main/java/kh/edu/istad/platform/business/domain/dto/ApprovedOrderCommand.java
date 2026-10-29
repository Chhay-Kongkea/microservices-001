package kh.edu.istad.platform.business.domain.dto;

public record ApprovedOrderCommand(
        String businessId,
        String orderId
) {
}
