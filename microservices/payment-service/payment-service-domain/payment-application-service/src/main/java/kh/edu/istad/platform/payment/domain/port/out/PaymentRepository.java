package kh.edu.istad.platform.payment.domain.port.out;

import kh.edu.istad.common.domain.valueobject.PaymentId;
import kh.edu.istad.platform.payment.domain.entity.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
    boolean existById(PaymentId paymentId);

}
