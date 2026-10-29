package kh.edu.istad.platform.payment.restapi.exception;

import kh.edu.istad.common.domain.dto.ApiErrorResponse;
import kh.edu.istad.platform.payment.domain.exception.PaymentDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage()
                )
        );

        return error(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                errors
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception) {

        String field = exception.getName();
        String message = field + " has an invalid value";

        if ("orderId".equals(field)) {
            message = "orderId must be a valid UUID";
        } else if ("customerId".equals(field)) {
            message = "customerId must be a valid UUID";
        } else if ("paymentId".equals(field)) {
            message = "paymentId must be a valid UUID";
        } else if ("paymentStatus".equals(field)) {
            message = "paymentStatus has an invalid value";
        }

        return error(
                HttpStatus.BAD_REQUEST,
                message,
                Map.of(field, message)
        );
    }

    @ExceptionHandler(PaymentDomainException.class)
    public ResponseEntity<ApiErrorResponse> handleDomainException(
            PaymentDomainException exception) {

        String message = exception.getMessage();

        HttpStatus status = message != null
                && message.startsWith("Payment not found:")
                ? HttpStatus.NOT_FOUND
                : HttpStatus.BAD_REQUEST;

        return error(
                status,
                message,
                Map.of()
        );
    }

    private ResponseEntity<ApiErrorResponse> error(
            HttpStatus status,
            String message,
            Map<String, String> errors) {

        return ResponseEntity.status(status).body(
                new ApiErrorResponse(
                        status.value(),
                        message,
                        errors,
                        LocalDateTime.now()
                )
        );
    }
}