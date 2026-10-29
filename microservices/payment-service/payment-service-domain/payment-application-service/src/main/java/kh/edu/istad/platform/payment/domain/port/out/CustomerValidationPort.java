package kh.edu.istad.platform.payment.domain.port.out;

import kh.edu.istad.common.domain.valueobject.CustomerId;

public interface CustomerValidationPort {

       void existById(CustomerId id);

}
