package kh.edu.istad.platform.order.domain.port.out;

import kh.edu.istad.common.domain.valueobject.BusinessId;

public interface BusinessValidationPort {

    void existById(BusinessId id);

}
