package kh.edu.istad.platform.payment.persistence.repository;

import kh.edu.istad.common.domain.valueobject.PaymentId;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {


}
