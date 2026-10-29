package kh.edu.istad.platform.payment.domain.service;

import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.event.PaymentCancelledEvent;
import kh.edu.istad.platform.payment.domain.event.PaymentCompletedEvent;
import kh.edu.istad.platform.payment.domain.event.PaymentFailedEvent;

public interface PaymentDomainService {


    PaymentCompletedEvent completePayment(Payment payment);
    PaymentCancelledEvent cancelPayment(Payment payment);
    PaymentFailedEvent failPayment(Payment payment);

}
