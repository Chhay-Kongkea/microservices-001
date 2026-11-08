package kh.edu.istad.platform.business.restapi.dto;

public record BusinessResponse(
        String businessId,
        String businessName,
        boolean active
) {
}
