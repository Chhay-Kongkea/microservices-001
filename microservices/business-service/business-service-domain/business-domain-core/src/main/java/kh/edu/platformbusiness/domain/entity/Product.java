package kh.edu.platformbusiness.domain.entity;

import kh.edu.istad.common.domain.entity.BaseEntity;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.ProductId;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.UUID;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;
    private Integer quantity;
    private boolean available;

    @PersistenceCreator
    public Product(ProductId id, String name, Money price,
                   Integer quantity, boolean available) {
        super.setId(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    private Product(Builder builder) {
        super.setId(
                builder.id != null
                        ? builder.id
                        : new ProductId(UUID.randomUUID())
        );
        name = builder.name;
        price = builder.price;
        quantity = builder.quantity;
        available = builder.available;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }


    public static final class Builder {
        private ProductId  id;
        private String name;
        private Money price;
        private Integer quantity;
        private boolean available;

        private Builder() {
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
