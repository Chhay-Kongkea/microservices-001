package kh.edu.istad.platform.payment.domain.service;

import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.event.PaymentCancelledEvent;
import kh.edu.istad.platform.payment.domain.event.PaymentCompletedEvent;
import kh.edu.istad.platform.payment.domain.event.PaymentFailedEvent;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public class PaymentDomainServiceImpl implements PaymentDomainService{
    @Override
    public PaymentCompletedEvent completePayment(Payment payment) {


        payment.initiatePayment();
        return new PaymentCompletedEvent(payment, ZonedDateTime.now(ZoneOffset.UTC), List.of());
    }

    @Override
    public PaymentCancelledEvent cancelPayment(Payment payment) {

        payment.updatePayment(PaymentStatus.CANCELED);

        return new PaymentCancelledEvent(payment, ZonedDateTime.now(ZoneOffset.UTC), List.of());
    }

    @Override
    public PaymentFailedEvent failPayment(Payment payment) {

        payment.updatePayment(PaymentStatus.FAILED);
        return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneOffset.UTC), List.of());
    }
}
