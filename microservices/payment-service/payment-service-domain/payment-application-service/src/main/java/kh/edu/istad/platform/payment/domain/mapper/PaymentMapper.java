
package kh.edu.istad.platform.payment.domain.mapper;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.common.domain.valueobject.PaymentId;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentResult;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentResult;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        imports = {PaymentId.class, UUID.class}
)
public interface PaymentMapper {

    @Mapping(
            target = "id",
            expression = "java(new PaymentId(UUID.randomUUID()))"
    )
    Payment fromInitiateCommandToDomain(InitiatePaymentCommand command);

    Payment fromUpdateCommandToDomain(
            UpdatePaymentCommand command
    );

    @Mapping(target = "paymentId", expression = "java(payment.getId().id())")
    InitiatePaymentResult fromDomainToInitiateResult(Payment payment);

    UpdatePaymentResult fromDomainToUpdateResult(
            Payment payment
    );

    default CustomerId map(UUID value) {
        return value == null ? null : new CustomerId(value);
    }


    default UUID map(CustomerId value) {
        return value == null ? null : value.value();
    }


    default PaymentId mapPaymentId(UUID value) {
        return value == null ? null : new PaymentId(value);
    }

    default UUID map(PaymentId value) {
        return value == null ? null : value.id();
    }

    default OrderId mapOrderId(UUID value) {
        return value == null ? null : new OrderId(value);
    }


    default UUID map(OrderId value) {
        return value == null ? null : value.value();
    }


    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }

    default BigDecimal map(Money value) {
        return value == null ? null : value.amount();
    }
}
