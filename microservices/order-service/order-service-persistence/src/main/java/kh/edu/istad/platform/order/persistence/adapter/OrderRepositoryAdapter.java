package kh.edu.istad.platform.order.persistence.adapter;

import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.order.domain.entity.Order;
import kh.edu.istad.platform.order.domain.port.out.OrderRepository;
import kh.edu.istad.platform.order.persistence.mapper.OrderPersistenceMapper;
import kh.edu.istad.platform.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;


    @Override
    public Order save(Order order) {
        return orderPersistenceMapper.toDomain(orderJpaRepository
                .save(orderPersistenceMapper.toEntity(order)));
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderJpaRepository.findById(orderId.id())
                .map(orderPersistenceMapper::toDomain);
    }

    @Override
    public boolean existById(OrderId orderId) {

        return orderJpaRepository.existsById(orderId.id());
    }
}
