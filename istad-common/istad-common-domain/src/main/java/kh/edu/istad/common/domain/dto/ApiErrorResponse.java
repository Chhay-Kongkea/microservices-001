package kh.edu.istad.common.domain.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ApiErrorResponse(
        int status,
        String message,
        Map<String, String> errors,
        LocalDateTime timestamp
) {

}
