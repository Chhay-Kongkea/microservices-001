package kh.edu.istad.platform.payment.domain.entity;

import kh.edu.istad.common.domain.entity.BaseEntity;
import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.payment.domain.exception.PaymentDomainException;
import kh.edu.istad.platform.payment.domain.valueobject.CreditEntryId;

public class CreditEntry extends BaseEntity<CreditEntryId> {

    private CustomerId customerId;
    private Money totalCreditAmount;

    public void addCreditAmount(Money amount) {
        if (amount == null) {
            throw new PaymentDomainException("Credit amount cannot be null");
        }

        if (amount.isLessThanZero()) {
            throw new PaymentDomainException("Credit amount must be greater than zero");
        }

        this.totalCreditAmount = this.totalCreditAmount.add(amount);
    }

    public void subtractCreditAmount(Money amount) {
        if (amount == null) {
            throw new PaymentDomainException("Credit amount cannot be null");
        }

        if (amount.isLessThanZero()) {
            throw new PaymentDomainException("Credit amount must be greater than zero");
        }

        if (this.totalCreditAmount == null) {
            throw new IllegalStateException("Total credit amount cannot be null");
        }

        if (this.totalCreditAmount.isLessThan(amount)) {
            throw new PaymentDomainException("Insufficient credit balance");
        }

        this.totalCreditAmount = this.totalCreditAmount.subtract(amount);
    }



    public CreditEntry(CustomerId customerId, Money totalCreditAmount) {
        this.customerId = customerId;
        this.totalCreditAmount = totalCreditAmount;
    }

    private CreditEntry(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        totalCreditAmount = builder.totalCreditAmount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getTotalCreditAmount() {
        return totalCreditAmount;
    }


    public static final class Builder {
        private CreditEntryId id;
        private CustomerId customerId;
        private Money totalCreditAmount;

        private Builder() {
        }

        public Builder id(CreditEntryId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder totalCreditAmount(Money val) {
            totalCreditAmount = val;
            return this;
        }

        public CreditEntry build() {
            return new CreditEntry(this);
        }
    }
}
