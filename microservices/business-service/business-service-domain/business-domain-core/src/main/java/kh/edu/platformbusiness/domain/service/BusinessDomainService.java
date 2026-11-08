package kh.edu.platformbusiness.domain.service;

import kh.edu.platformbusiness.domain.entity.Business;
import kh.edu.platformbusiness.domain.event.BusinessRegisteredEvent;
import kh.edu.platformbusiness.domain.event.OrderApprovalEvent;
import kh.edu.platformbusiness.domain.event.OrderApprovedEvent;
import kh.edu.platformbusiness.domain.event.OrderRejectedEvent;

public interface BusinessDomainService {

    BusinessRegisteredEvent registerBusiness(Business business);
    OrderApprovalEvent approvalOrder(Business business);
    OrderApprovedEvent approvedOrder(Business business);
    OrderRejectedEvent rejectOrder(Business business);

}
