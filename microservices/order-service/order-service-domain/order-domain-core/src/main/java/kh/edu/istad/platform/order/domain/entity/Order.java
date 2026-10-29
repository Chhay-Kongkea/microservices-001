package kh.edu.istad.platform.order.domain.entity;

import kh.edu.istad.platform.order.domain.exception.OrderDomainException;
import kh.edu.istad.common.domain.entity.AggregateRoot;
import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.common.domain.valueobject.OrderStatus;
import kh.edu.istad.platform.order.domain.valueobject.StreetAddress;
import kh.edu.istad.platform.order.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;
    private final BusinessId businessId;
    private final StreetAddress streetAddress;
    private Money price;
    private final List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;



    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        businessId = builder.businessId;
        streetAddress = builder.streetAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public StreetAddress getStreetAddress() {
        return streetAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessage() {
        return failureMessages;
    }

    public Order(CustomerId customerId, BusinessId businessId, StreetAddress streetAddress, Money price, List<OrderItem> items) {
        this.customerId = customerId;
        this.businessId = businessId;
        this.streetAddress = streetAddress;
        this.price = price;
        this.items = items;
    }


    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private BusinessId businessId;
        private StreetAddress streetAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessage;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder streetAddress(StreetAddress val) {
            streetAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessage(List<String> val) {
            failureMessage = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }


    public void initializeOrder(){

        if(super.getId() != null || orderStatus != null){
            throw new OrderDomainException("Order has already been initialized");
        }

        super.setId(new OrderId(UUID.randomUUID()));

        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        price = total();

    }


    public Money total() {
        return items.stream()
                .map(OrderItem::getOrderItemSubtotalPrice)
                .reduce(Money.ZERO, Money::add);
    }

    public void pay(){
        if(orderStatus != OrderStatus.PENDING){
            throw new OrderDomainException("Order status is not correct for pay operation");
        }


        orderStatus = OrderStatus.PAID;
    }



    public void approve(){
        if (orderStatus != OrderStatus.PAID){
            throw new OrderDomainException("Order status is not correct for approve operation");
        }

        orderStatus = OrderStatus.APPROVED;
    }


    public void initCancel(List<String> failureMessages){
        if(orderStatus != OrderStatus.PAID){
            throw new OrderDomainException("Order status is not correct for init cancel");
        }

        orderStatus = OrderStatus.CANCELLING;
        updateFailerMessage(failureMessages);
    }



    public void cancel(List<String> failureMessages){
        if(orderStatus != OrderStatus.PENDING || orderStatus != OrderStatus.CANCELLING){
            throw new OrderDomainException("Order status is not correct for cancel");
        }

        orderStatus = OrderStatus.CANCELLED;
        updateFailerMessage(failureMessages);
    }


    private void updateFailerMessage(List<String> failureMessages){
        if(this.failureMessages != null && failureMessages != null){
            this.failureMessages.addAll(failureMessages.stream()
                    .filter(message -> message != null && !message.isEmpty())
                    .toList());
        }else {
            this.failureMessages = failureMessages;

        }
    }
}
