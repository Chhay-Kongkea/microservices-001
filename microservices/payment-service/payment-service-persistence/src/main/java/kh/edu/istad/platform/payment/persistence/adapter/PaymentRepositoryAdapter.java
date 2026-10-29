package kh.edu.istad.platform.payment.persistence.adapter;

import kh.edu.istad.common.domain.valueobject.PaymentId;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.port.out.PaymentRepository;
import kh.edu.istad.platform.payment.persistence.mapper.PaymentPersistentMapper;
import kh.edu.istad.platform.payment.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentPersistentMapper paymentPersistentMapper;

    @Override
    public Payment save(Payment payment) {
        return paymentPersistentMapper.toDomain(paymentJpaRepository
                .save(paymentPersistentMapper.toEntity(payment)));
    }

    @Override
    public boolean existById(PaymentId paymentId) {
        return paymentJpaRepository.existsById(paymentId.id());
    }
}
