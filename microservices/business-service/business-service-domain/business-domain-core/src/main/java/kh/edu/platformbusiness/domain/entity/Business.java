package kh.edu.platformbusiness.domain.entity;

import kh.edu.istad.common.domain.entity.AggregateRoot;
import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.platformbusiness.domain.exception.BusinessDomainException;
import kh.edu.platformbusiness.domain.valueobject.BusinessContact;
import kh.edu.platformbusiness.domain.valueobject.BusinessName;
import kh.edu.platformbusiness.domain.valueobject.OrderApprovalId;
import org.springframework.data.annotation.PersistenceCreator;

public class Business extends AggregateRoot<BusinessId> {

    private BusinessName businessName;
    private BusinessContact businessContact;
    private OrderApproval orderApproval;
    private boolean active;
    private OrderDetail orderDetail;

    public void validateBusiness() {
        if (getId() == null || businessName == null || businessContact == null) {
            throw new BusinessDomainException(
                    "Business identity, name, and contact are required"
            );
        }
    }

    public void initializeBusiness() {
        validateBusiness();
        this.active = true;
    }

    public void validateOrder() {
        if (!active) {
            throw new BusinessDomainException(
                    "Inactive business cannot process orders"
            );
        }

        if (orderDetail == null) {
            throw new BusinessDomainException(
                    "Order detail is required"
            );
        }

        if (orderDetail.getProducts() == null
                || orderDetail.getProducts().isEmpty()) {
            throw new BusinessDomainException(
                    "Order must contain at least one product"
            );
        }

        for (Product product : orderDetail.getProducts()) {
            if (product == null) {
                throw new BusinessDomainException(
                        "Order cannot contain a null product"
                );
            }

            if (!product.isAvailable()) {
                throw new BusinessDomainException(
                        "Product is not available: " + product.getName()
                );
            }

            if (product.getQuantity() == null || product.getQuantity() <= 0) {
                throw new BusinessDomainException(
                        "Product quantity must be greater than zero"
                );
            }
        }
    }

    public void constructOrderApproval(OrderApproval orderApproval) {
        if (!active) {
            throw new BusinessDomainException(
                    "Inactive business cannot create order approval"
            );
        }

        if (orderApproval == null) {
            throw new BusinessDomainException(
                    "Order approval is required"
            );
        }

        if (this.orderApproval != null) {
            throw new BusinessDomainException(
                    "Order approval has already been created"
            );
        }

        this.orderApproval = orderApproval;
    }





    @PersistenceCreator
    public Business(BusinessName businessName, BusinessContact businessContact, OrderApproval orderApproval, boolean active, OrderDetail orderDetail) {
        this.businessName = businessName;
        this.businessContact = businessContact;
        this.orderApproval = orderApproval;
        this.active = active;
        this.orderDetail = orderDetail;
    }

    private Business(Builder builder) {
        super.setId(builder.id);
        businessName = builder.businessName;
        businessContact = builder.businessContact;
        orderApproval = builder.orderApproval;
        active = builder.active;
        orderDetail = builder.orderDetail;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public BusinessName getBusinessName() {
        return businessName;
    }

    public BusinessContact getBusinessContact() {
        return businessContact;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public boolean isActive() {
        return active;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }


    public static final class Builder {
        private BusinessId id;
        private BusinessName businessName;
        private BusinessContact businessContact;
        private OrderApproval orderApproval;
        private boolean active;
        private OrderDetail orderDetail;

        private Builder() {
        }

        public Builder id(BusinessId val) {
            id = val;
            return this;
        }

        public Builder businessName(BusinessName val) {
            businessName = val;
            return this;
        }

        public Builder businessContact(BusinessContact val) {
            businessContact = val;
            return this;
        }

        public Builder orderApproval(OrderApproval val) {
            orderApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder orderDetail(OrderDetail val) {
            orderDetail = val;
            return this;
        }

        public Business build() {
            return new Business(this);
        }
    }
}
