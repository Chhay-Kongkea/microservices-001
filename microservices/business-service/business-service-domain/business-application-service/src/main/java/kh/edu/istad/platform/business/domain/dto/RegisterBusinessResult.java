package kh.edu.istad.platform.business.domain.dto;

public record RegisterBusinessResult(
        String businessId,
        String businessName,
        boolean active
) {
}
