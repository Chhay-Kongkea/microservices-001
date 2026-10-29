package kh.edu.istad.platform.order.persistence.mapper;

import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.entity.OrderItem;
import kh.edu.istad.platform.order.domain.valueobject.StreetAddress;
import kh.edu.istad.platform.order.persistence.entity.OrderEntity;
import kh.edu.istad.platform.order.persistence.entity.OrderItemEntity;
import kh.edu.istad.platform.order.persistence.entity.StreetAddressEmbeddable;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {


    @Mapping(target = "id.id", source = "orderId")
    @Mapping(target = "customerId.id", source = "customerId")
    @Mapping(target = "businessId.id", source = "businessId")
    @Mapping(target = "trackingId.id", source = "trackingId")
    @Mapping(target = "streetAddress", source = "streetAddress")
    Order toDomain(OrderEntity order);


    @Mapping(target = "orderId", source = "id.id")
    @Mapping(target = "customerId", source = "customerId.id")
    @Mapping(target = "businessId", source = "businessId.id")
    @Mapping(target = "trackingId", source = "trackingId.id")
    @Mapping(target = "streetAddress", source = "streetAddress")
    @Mapping(target = "price", source = "price")
    OrderEntity toEntity(Order order);

    StreetAddressEmbeddable toEmbeddable(StreetAddress address);

    StreetAddress toDomain(StreetAddressEmbeddable address);



    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "productId", source = "product.id.id")
    @Mapping(target = "price", source = "price.amount")
    @Mapping(target = "subtotal", source = "subtotal.amount")
    @Mapping(target = "order", ignore = true)
    OrderItemEntity toEntity(OrderItem orderItem);

    @AfterMapping
    default void linkOrderItems(@MappingTarget OrderEntity orderEntity) {
        if (orderEntity.getItems() == null) {
            return;
        }

        for (OrderItemEntity item : orderEntity.getItems()) {
            item.setOrder(orderEntity);
        }
    }


    @Mapping(target = "id.id", source = "orderItemId")
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    OrderItem toDomain(OrderItemEntity entity);


    // BigDecimal -> Money
    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }

    // Money -> BigDecimal
    default BigDecimal map(Money value) {
        return value == null ? null : value.amount();
    }
}
