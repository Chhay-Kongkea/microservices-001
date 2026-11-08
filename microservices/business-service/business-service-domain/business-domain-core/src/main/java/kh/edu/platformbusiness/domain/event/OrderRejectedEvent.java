package kh.edu.platformbusiness.domain.event;

import kh.edu.istad.common.domain.event.DomainEvent;
import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.platformbusiness.domain.entity.Business;
import kh.edu.platformbusiness.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent implements DomainEvent<Business> {

    private final OrderApproval orderApproval;
    private final BusinessId businessId;
    private final List<String> failureMessage;
    private final ZonedDateTime createdAt;

    public OrderRejectedEvent(OrderApproval orderApproval, BusinessId businessId, List<String> failureMessage, ZonedDateTime createdAt) {
        this.orderApproval = orderApproval;
        this.businessId = businessId;
        this.failureMessage = failureMessage;
        this.createdAt = createdAt;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public List<String> getFailureMessage() {
        return failureMessage;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
