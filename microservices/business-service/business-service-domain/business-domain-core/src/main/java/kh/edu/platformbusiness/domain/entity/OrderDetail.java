package kh.edu.platformbusiness.domain.entity;

import kh.edu.istad.common.domain.entity.BaseEntity;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.common.domain.valueobject.OrderStatus;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.List;

public class OrderDetail extends BaseEntity<OrderId> {

    private OrderStatus orderStatus;
    private Money totalAmount;
    private List<Product> products;

    @PersistenceCreator
    public OrderDetail(OrderStatus orderStatus, Money totalAmount, List<Product> products) {
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.products = products;
    }

    private OrderDetail(Builder builder) {
       super.setId(builder.id);
        orderStatus = builder.orderStatus;
        totalAmount = builder.totalAmount;
        products = builder.products;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public List<Product> getProducts() {
        return products;
    }


    public static final class Builder {
        private OrderId id;
        private OrderStatus orderStatus;
        private Money totalAmount;
        private List<Product> products;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
