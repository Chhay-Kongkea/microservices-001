package kh.edu.istad.platform.payment.domain.event;

import kh.edu.istad.common.domain.event.DomainEvent;
import kh.edu.istad.platform.payment.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentFailedEvent implements DomainEvent<Payment> {
    private final Payment payment;
    private final ZonedDateTime createdAt;
    private final List<String> failureMessages;


    public PaymentFailedEvent(Payment payment, ZonedDateTime zonedDateTime, List<String> failureMessages) {
        this.payment = payment;
        this.createdAt = zonedDateTime;
        this.failureMessages = failureMessages;
    }

    public Payment getPayment() {
        return payment;
    }

    public ZonedDateTime getZonedDateTime() {
        return createdAt;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }
}
