package kh.edu.istad.platform.payment.persistence.mapper;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.common.domain.valueobject.PaymentId;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PaymentPersistentMapper {


    Payment toDomain(PaymentEntity entity);
    PaymentEntity toEntity(Payment payment);



    default PaymentId mapPaymentId(UUID value) {

        return value == null ? null : new PaymentId(value);
    }

    // CustomerId -> UUID
    default UUID map(PaymentId value) {

        return value == null ? null : value.id();
    }

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
