
package kh.edu.istad.platform.order.domain.entity;

import kh.edu.istad.common.domain.entity.BaseEntity;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.order.domain.exception.OrderDomainException;
import kh.edu.istad.platform.order.domain.valueobject.OrderItemId;

import java.util.UUID;

public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final Integer quantity;
    private final Money price;
    private final Money subtotal;

    private OrderItem(Builder builder) {
        super.setId(
                builder.id != null
                        ? builder.id
                        : new OrderItemId(UUID.randomUUID())
        );

        this.orderId = builder.orderId;
        this.product = builder.product;
        this.quantity = builder.quantity;

        validateQuantity(builder.quantity);

        // Prefer the resolved product's price when available.
        this.price = builder.product != null
                ? builder.product.getPrice()
                : builder.price;

        // Calculate subtotal instead of relying on the caller.
        this.subtotal = this.price != null
                ? this.price.multiply(this.quantity)
                : builder.subtotal;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new OrderDomainException(
                    "Order item quantity must be greater than zero"
            );
        }
    }

    public Money getOrderItemSubtotalPrice() {
        if (subtotal == null) {
            throw new OrderDomainException(
                    "Order item subtotal has not been initialized"
            );
        }

        return subtotal;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubtotal() {
        return subtotal;
    }

    public static final class Builder {

        private OrderItemId id;
        private OrderId orderId;
        private Product product;
        private Integer quantity;
        private Money price;
        private Money subtotal;

        private Builder() {
        }

        public Builder id(OrderItemId id) {
            this.id = id;
            return this;
        }

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder subtotal(Money subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
