
package kh.edu.platformbusiness.domain.entity;

import kh.edu.istad.common.domain.entity.BaseEntity;
import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.platformbusiness.domain.exception.BusinessDomainException;
import kh.edu.platformbusiness.domain.valueobject.OrderApprovalId;
import kh.edu.platformbusiness.domain.valueobject.OrderApprovalStatus;
import org.springframework.data.annotation.PersistenceCreator;

public class OrderApproval extends BaseEntity<OrderApprovalId> {

    private BusinessId businessId;
    private OrderId orderId;
    private OrderApprovalStatus orderApprovalStatus;

    public OrderApproval(BusinessId businessId, OrderId orderId, OrderApprovalStatus orderApprovalStatus) {
        this.businessId = businessId;
        this.orderId = orderId;
        this.orderApprovalStatus = orderApprovalStatus;
    }

    @PersistenceCreator
    public static OrderApproval from(
            BusinessId businessId,
            OrderId orderId,
            OrderApprovalStatus orderApprovalStatus
    ) {
        return OrderApproval.newBuilder()
                .businessId(businessId)
                .orderId(orderId)
                .orderApprovalStatus(orderApprovalStatus)
                .build();
    }

    public OrderApproval(Builder builder) {
        super.setId(builder.id);
        this.businessId = builder.businessId;
        this.orderId = builder.orderId;
        this.orderApprovalStatus = builder.orderApprovalStatus;
    }

    public void approve() {
        if (orderApprovalStatus == OrderApprovalStatus.APPROVED) {
            throw new BusinessDomainException(
                    "Order has already been approved"
            );
        }

        this.orderApprovalStatus = OrderApprovalStatus.APPROVED;
    }

    public void reject() {
        if (orderApprovalStatus == OrderApprovalStatus.REJECTED) {
            throw new BusinessDomainException(
                    "Order has already been rejected"
            );
        }

        this.orderApprovalStatus = OrderApprovalStatus.REJECTED;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderApprovalStatus getOrderApprovalStatus() {
        return orderApprovalStatus;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private OrderApprovalId id;
        private BusinessId businessId;
        private OrderId orderId;
        private OrderApprovalStatus orderApprovalStatus;

        private Builder() {
        }

        public Builder id(OrderApprovalId id) {
            this.id = id;
            return this;
        }

        public Builder businessId(BusinessId businessId) {
            this.businessId = businessId;
            return this;
        }

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderApprovalStatus(
                OrderApprovalStatus orderApprovalStatus
        ) {
            this.orderApprovalStatus = orderApprovalStatus;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
