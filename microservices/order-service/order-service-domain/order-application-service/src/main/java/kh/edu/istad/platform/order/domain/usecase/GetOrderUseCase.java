package kh.edu.istad.platform.order.domain.usecase;


import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.order.domain.dto.InitiateOrderResult;
import kh.edu.istad.platform.order.domain.exception.OrderDomainException;
import kh.edu.istad.platform.order.domain.mapper.OrderMapper;
import kh.edu.istad.platform.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public Optional<InitiateOrderResult> get(UUID id){

        if(id == null){
            throw new OrderDomainException("Order id can't be null");
        }

        return orderRepository.findById(new OrderId(id))
                .map(orderMapper::toInitiateOrderResult);

    }
}
