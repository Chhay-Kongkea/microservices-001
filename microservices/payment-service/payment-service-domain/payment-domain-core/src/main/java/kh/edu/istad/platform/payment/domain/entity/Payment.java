package kh.edu.istad.platform.payment.domain.entity;

import kh.edu.istad.common.domain.entity.AggregateRoot;
import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.common.domain.valueobject.PaymentId;
import kh.edu.istad.platform.payment.domain.exception.PaymentDomainException;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

import java.time.ZonedDateTime;

public class Payment extends AggregateRoot<PaymentId> {

    private OrderId orderId;
    private CustomerId customerId;
    private Money price;
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;


    public void validatePayment() {
        if (orderId == null) {
            throw new PaymentDomainException("Order ID cannot be null");
        }

        if (customerId == null) {
            throw new PaymentDomainException("Customer ID cannot be null");
        }

        if (price == null) {
            throw new PaymentDomainException("Payment price cannot be null");
        }

        if (price.isLessThanZero()) {
            throw new PaymentDomainException("Payment price must be greater than zero");
        }
    }

    public void initiatePayment() {
        validatePayment();

        if (paymentStatus != null) {
            throw new PaymentDomainException("Payment has already been initiated");
        }

        this.paymentStatus = PaymentStatus.COMPLETED;
        this.createdAt = ZonedDateTime.now();
    }

    


    public void updatePayment(PaymentStatus newStatus) {
        if (newStatus == null) {
            throw new PaymentDomainException("Payment status cannot be null");
        }

        if (paymentStatus == null) {
            throw new PaymentDomainException("Payment has not been initiated");
        }

        if (paymentStatus == PaymentStatus.CANCELED
                || paymentStatus == PaymentStatus.FAILED) {
            throw new PaymentDomainException(
                    "Cannot update a canceled or failed payment"
            );
        }

        this.paymentStatus = newStatus;
    }



    public Payment(OrderId orderId, CustomerId customerId, Money price, PaymentStatus paymentStatus, ZonedDateTime createdAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    private Payment(Builder builder) {
        super.setId(builder.id);
        orderId = builder.orderId;
        customerId = builder.customerId;
        price = builder.price;
        paymentStatus = builder.paymentStatus;
        createdAt = builder.createdAt;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getPrice() {
        return price;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }


    public static final class Builder {
        private PaymentId id;
        private OrderId orderId;
        private CustomerId customerId;
        private Money price;
        private PaymentStatus paymentStatus;
        private ZonedDateTime createdAt;

        private Builder() {
        }

        public Builder id(PaymentId val) {
            id = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
