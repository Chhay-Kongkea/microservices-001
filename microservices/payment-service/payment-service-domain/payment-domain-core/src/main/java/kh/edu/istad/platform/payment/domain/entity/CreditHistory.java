package kh.edu.istad.platform.payment.domain.entity;

import kh.edu.istad.common.domain.entity.BaseEntity;
import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.payment.domain.valueobject.CreditHistoryId;
import kh.edu.istad.platform.payment.domain.valueobject.TransactionType;

public class CreditHistory extends BaseEntity<CreditHistoryId> {

    private CustomerId customerId;
    private Money amount;
    private TransactionType transactionType;

    public CreditHistory(CustomerId customerId, Money amount, TransactionType transactionType) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    private CreditHistory(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        amount = builder.amount;
        transactionType = builder.transactionType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Money getAmount() {
        return amount;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }


    public static final class Builder {
        private CreditHistoryId id;
        private CustomerId customerId;
        private Money amount;
        private TransactionType transactionType;

        private Builder() {
        }

        public Builder id(CreditHistoryId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder transactionType(TransactionType val) {
            transactionType = val;
            return this;
        }

        public CreditHistory build() {
            return new CreditHistory(this);
        }
    }
}
