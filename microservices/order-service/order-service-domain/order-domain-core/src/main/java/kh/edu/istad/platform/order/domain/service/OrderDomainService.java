package kh.edu.istad.platform.order.domain.service;

import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.event.OrderCancelledEvent;
import kh.edu.istad.platform.order.domain.event.OrderCreatedEvent;
import kh.edu.istad.platform.order.domain.event.OrderPaidEvent;

public interface OrderDomainService {

    OrderCreatedEvent initiatedOrder(Order order);
    OrderCancelledEvent cancelOrder(Order order);
    OrderPaidEvent paidOrder(Order order);

}
