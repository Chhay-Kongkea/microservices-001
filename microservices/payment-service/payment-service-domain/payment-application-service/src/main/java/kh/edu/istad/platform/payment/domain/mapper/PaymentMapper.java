package kh.edu.istad.platform.payment.domain.mapper;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentResult;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentResult;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment fromInitiateCommandToDomain(InitiatePaymentCommand initiatePaymentCommand);
    Payment fromUpdateCommandToDomain(UpdatePaymentCommand updatePaymentCommand);
    InitiatePaymentResult fromDomainToInitiateResult(Payment payment);
    UpdatePaymentResult fromDomainToUpdateResult(Payment payment);

    // UUID -> CustomerId
    default CustomerId map(UUID value) {

        return value == null ? null : new CustomerId(value);
    }

    // CustomerId -> UUID
    default UUID map(CustomerId value) {

        return value == null ? null : value.id();
    }


    default OrderId mapOrderId(UUID value) {

        return value == null ? null : new OrderId(value);
    }


    default UUID map(OrderId value) {

        return value == null ? null : value.id();
    }



    // BigDecimal -> Money
    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }

    // Money -> BigDecimal
    default BigDecimal map(Money value) {
        return value == null ? null : value.amount();
    }
}
