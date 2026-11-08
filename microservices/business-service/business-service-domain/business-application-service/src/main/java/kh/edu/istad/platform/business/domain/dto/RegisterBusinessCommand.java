package kh.edu.istad.platform.business.domain.dto;

import kh.edu.platformbusiness.domain.entity.OrderApproval;
import kh.edu.platformbusiness.domain.entity.OrderDetail;
import kh.edu.platformbusiness.domain.valueobject.BusinessContact;
import kh.edu.platformbusiness.domain.valueobject.BusinessName;

public record RegisterBusinessCommand(
        BusinessName businessName,
        BusinessContact businessContact,
        OrderApproval orderApproval,
        boolean active,
        OrderDetail orderDetail
) {
    @Override
    public BusinessName businessName() {
        return businessName;
    }

    @Override
    public BusinessContact businessContact() {
        return businessContact;
    }

    @Override
    public OrderApproval orderApproval() {
        return orderApproval;
    }

    @Override
    public boolean active() {
        return active;
    }

    @Override
    public OrderDetail orderDetail() {
        return orderDetail;
    }
}
