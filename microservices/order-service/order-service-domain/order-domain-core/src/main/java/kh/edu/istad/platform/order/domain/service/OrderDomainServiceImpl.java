package kh.edu.istad.platform.order.domain.service;

import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.event.OrderCancelledEvent;
import kh.edu.istad.platform.order.domain.event.OrderCreatedEvent;
import kh.edu.istad.platform.order.domain.event.OrderPaidEvent;
import kh.edu.istad.common.domain.valueobject.OrderStatus;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService{
    @Override
    public OrderCreatedEvent initiatedOrder(Order order) {

        order.initializeOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneOffset.UTC));
    }

    @Override
    public OrderCancelledEvent cancelOrder(Order order) {
        if(order.getOrderStatus().equals(OrderStatus.PENDING))
        {
            order.cancel(List.of("Order status is not correct for cancel"));
        }

        if(order.getOrderStatus().equals(OrderStatus.PAID))
        {
            order.initCancel(List.of("Order status is not correct for cancel"));
        }

        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneOffset.UTC));
    }

    @Override
    public OrderPaidEvent paidOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneOffset.UTC));
    }
}
