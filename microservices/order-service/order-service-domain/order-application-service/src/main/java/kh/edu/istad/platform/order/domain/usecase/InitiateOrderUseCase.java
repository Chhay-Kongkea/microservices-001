package kh.edu.istad.platform.order.domain.usecase;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.platform.order.domain.dto.InitiateOrderCommand;
import kh.edu.istad.platform.order.domain.dto.InitiateOrderResult;
import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.mapper.OrderMapper;
import kh.edu.istad.platform.order.domain.port.out.BusinessValidationPort;
import kh.edu.istad.platform.order.domain.port.out.CustomerValidationPort;
import kh.edu.istad.platform.order.domain.port.out.OrderRepository;
import kh.edu.istad.platform.order.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitiateOrderUseCase {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerValidationPort customerValidationPort;
    private final BusinessValidationPort businessValidationPort;

    @Transactional
    public InitiateOrderResult execute(InitiateOrderCommand command){

        customerValidationPort.existById(new CustomerId(command.customerId()));

        businessValidationPort.existById(new BusinessId(command.businessId()));


        Order order = orderMapper.toEntity(command);



        orderDomainService.initiatedOrder(order);

        System.out.println("Price after domain service: " + order.getPrice());

        return orderMapper.toInitiateOrderResult(orderRepository.save(order));


    }
}
