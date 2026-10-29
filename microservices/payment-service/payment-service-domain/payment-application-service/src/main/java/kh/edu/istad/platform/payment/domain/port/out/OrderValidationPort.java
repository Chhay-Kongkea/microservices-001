package kh.edu.istad.platform.payment.domain.port.out;

import kh.edu.istad.common.domain.valueobject.OrderId;

public interface OrderValidationPort {

    void existById(OrderId orderId);
}
