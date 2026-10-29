package kh.edu.istad.platform.order.domain.mapper;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.ProductId;
import kh.edu.istad.platform.order.domain.dto.*;
import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.entity.OrderItem;
import kh.edu.istad.platform.order.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order fromCancelOrderCommandToEntity(CancelOrderCommand command);

    Order fromPayOrderCommandToEntity(PayOrderCommand command);


    @Mapping(target = "items", source = "items")
    Order toEntity(InitiateOrderCommand command);

    @Mapping(target = "orderId", source = "id.id")
    InitiateOrderResult toInitiateOrderResult(Order order);

    CancelOrderResult toCancelOrderResult(Order order);

    PayOrderResult toPayOrderResult(Order order);

    // Convert the request item into the domain OrderItem.
    default OrderItem toOrderItem(InitiateOrderItemCommand command) {
        if (command == null) {
            return null;
        }

        Product product = Product.newBuilder()
                .id(new ProductId(command.productId()))
                .price(map(command.price()))
                .build();

        return OrderItem.newBuilder()
                .product(product)
                .quantity(command.quantity())
                .build();
    }

    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }

    default BigDecimal map(Money value) {
        return value == null ? null : value.amount();
    }

    default CustomerId map(UUID value) {
        return value == null ? null : new CustomerId(value);
    }

    default UUID map(CustomerId value) {
        return value == null ? null : value.id();
    }

    default BusinessId mapBusinessId(UUID value) {
        return value == null ? null : new BusinessId(value);
    }

    default UUID map(BusinessId value) {
        return value == null ? null : value.id();
    }
}