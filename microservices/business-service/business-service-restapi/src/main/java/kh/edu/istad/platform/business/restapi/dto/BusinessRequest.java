
package kh.edu.istad.platform.business.restapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kh.edu.istad.common.domain.valueobject.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record BusinessRequest(

        @NotNull(message = "Business name is required")
        @Valid
        BusinessNameRequest businessName,

        @NotNull(message = "Business contact is required")
        @Valid
        BusinessContactRequest businessContact,

        @NotNull(message = "Order approval is required")
        @Valid
        OrderApprovalRequest orderApproval,

        @NotNull(message = "Active status is required")
        Boolean active,

        @Valid
        OrderDetailRequest orderDetail

) {

        public record BusinessNameRequest(

                @NotBlank(message = "Legal name is required")
                @Size(max = 150, message = "Legal name must not exceed 150 characters")
                String legalName,

                @NotBlank(message = "Display name is required")
                @Size(max = 100, message = "Display name must not exceed 100 characters")
                String displayName

        ) {
        }

        public record BusinessContactRequest(

                @NotBlank(message = "Email is required")
                @Email(message = "Email must be valid")
                @Size(max = 254, message = "Email must not exceed 254 characters")
                String email,

                @NotBlank(message = "Phone number is required")
                @Pattern(
                        regexp = "^\\+?[0-9]{8,15}$",
                        message = "Phone number must contain 8 to 15 digits, optionally starting with +"
                )
                String phoneNumber

        ) {
        }

        public record OrderApprovalRequest(

                @NotNull(message = "Order approval status is required")
                kh.edu.platformbusiness.domain.valueobject.OrderApprovalStatus
                orderApprovalStatus

        ) {
        }

        public record OrderDetailRequest(

                @NotNull(message = "Order status is required")
                OrderStatus orderStatus,

                @NotNull(message = "Total amount is required")
                @Positive(message = "Total amount must be greater than zero")
                BigDecimal totalAmount,

                @NotEmpty(message = "At least one product is required")
                List<@Valid ProductRequest> products

        ) {
        }

        public record ProductRequest(

                @NotBlank(message = "Product name is required")
                @Size(max = 150, message = "Product name must not exceed 150 characters")
                String name,

                @NotNull(message = "Product price is required")
                @Positive(message = "Product price must be greater than zero")
                BigDecimal price,

                @NotNull(message = "Quantity is required")
                @Positive(message = "Quantity must be greater than zero")
                Integer quantity,

                @NotNull(message = "Product availability is required")
                Boolean available

        ) {
        }
}
