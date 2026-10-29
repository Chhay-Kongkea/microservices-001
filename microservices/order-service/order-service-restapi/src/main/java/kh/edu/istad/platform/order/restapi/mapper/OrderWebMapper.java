package kh.edu.istad.platform.order.restapi.mapper;


import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.order.domain.dto.*;
import kh.edu.istad.platform.order.restapi.dto.*;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

    InitiateOrderCommand toInitiateCommand(InitiateOrderRequest request);
    CancelOrderCommand toCancelCommand(CancelOrderRequest request);
    PayOrderCommand toPayOrderCommand(PayOrderRequest request);

    InitiateOrderResponse toInitiateResponse(InitiateOrderResult result);
    CancelOrderResponse toCancelResponse(CancelOrderResult result);
    PayOrderResponse toPayResponse(PayOrderResult result);
    OrderResponse toResponse(InitiateOrderResult result);

    default Money map(BigDecimal value) {
        return value == null ? null : new Money(value);
    }
}
