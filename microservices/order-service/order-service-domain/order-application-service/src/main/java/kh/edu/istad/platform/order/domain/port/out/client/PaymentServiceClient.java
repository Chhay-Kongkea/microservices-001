package kh.edu.istad.platform.order.domain.port.out.client;

import kh.edu.istad.platform.order.domain.dto.PaymentResult;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentServiceClient {

    Mono<PaymentResult> initiatePayment(
            UUID orderId,
            UUID customerId,
            BigDecimal price
    );

}
