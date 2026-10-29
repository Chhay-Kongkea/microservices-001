
package kh.edu.platformbusiness.domain.service;

import kh.edu.platformbusiness.domain.entity.Business;
import kh.edu.platformbusiness.domain.entity.OrderApproval;
import kh.edu.platformbusiness.domain.event.BusinessRegisteredEvent;
import kh.edu.platformbusiness.domain.event.OrderApprovalEvent;
import kh.edu.platformbusiness.domain.event.OrderApprovedEvent;
import kh.edu.platformbusiness.domain.event.OrderRejectedEvent;
import kh.edu.platformbusiness.domain.exception.BusinessDomainException;

import java.time.ZonedDateTime;
import java.util.List;

public class BusinessDomainServiceImpl implements BusinessDomainService {

    @Override
    public BusinessRegisteredEvent registerBusiness(Business business) {
        if (business == null) {
            throw new BusinessDomainException(
                    "Business is required"
            );
        }

        business.validateBusiness();
        business.initializeBusiness();

        return new BusinessRegisteredEvent(
                business,
                ZonedDateTime.now()
        );
    }

    @Override
    public OrderApprovalEvent approvalOrder(Business business) {
        if (business == null) {
            throw new BusinessDomainException(
                    "Business is required"
            );
        }


        business.validateOrder();

        OrderApproval approval = business.getOrderApproval();

        if (approval == null) {
            throw new BusinessDomainException(
                    "Order approval is required"
            );
        }


        return new OrderApprovalEvent(
                approval,
                business.getId(),
                List.of(),
                ZonedDateTime.now()
        );
    }

    @Override
    public OrderApprovedEvent approvedOrder(Business business) {
        if (business == null) {
            throw new BusinessDomainException(
                    "Business is required"
            );
        }

        business.validateOrder();

        OrderApproval approval = business.getOrderApproval();

        if (approval == null) {
            throw new BusinessDomainException(
                    "Order approval is required"
            );
        }

        approval.approve();

        return new OrderApprovedEvent(
                business.getOrderApproval(),
                business.getId(),
                List.of(),
                ZonedDateTime.now()
        );
    }

    @Override
    public OrderRejectedEvent rejectOrder(Business business) {
        if (business == null) {
            throw new BusinessDomainException(
                    "Business is required"
            );
        }

        OrderApproval approval = business.getOrderApproval();

        if (approval == null) {
            throw new BusinessDomainException(
                    "Order approval is required"
            );
        }

        approval.reject();

        return new OrderRejectedEvent(
                business.getOrderApproval(),
                business.getId(),
                List.of(),
                ZonedDateTime.now()
        );
    }
}
