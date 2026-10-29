package kh.edu.istad.platform.order.domain.usecase;


import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.order.domain.dto.PayOrderCommand;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.exception.OrderDomainException;
import kh.edu.istad.platform.order.domain.mapper.OrderMapper;
import kh.edu.istad.platform.order.domain.port.out.CustomerValidationPort;
import kh.edu.istad.platform.order.domain.port.out.OrderRepository;
import kh.edu.istad.platform.order.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PayOrderUseCase {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerValidationPort customerValidationPort;

    @Transactional
    public PayOrderResult payOrder(PayOrderCommand command){

        if(orderRepository.existById(new OrderId(command.orderId()))){
            throw new OrderDomainException("Order does not exists");
        }

        customerValidationPort.existById(new CustomerId(command.customerId()));

        Order order = orderMapper.fromPayOrderCommandToEntity(command);

        orderDomainService.paidOrder(order);

        return orderMapper.toPayOrderResult(orderRepository.save(order));

    }


}
