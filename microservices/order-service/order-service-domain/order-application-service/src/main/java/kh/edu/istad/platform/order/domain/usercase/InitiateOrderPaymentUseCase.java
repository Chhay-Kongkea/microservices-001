package kh.edu.istad.platform.order.domain.usercase;

import kh.edu.istad.platform.order.domain.dto.PaymentResult;
import kh.edu.istad.platform.order.domain.port.out.client.PaymentServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitiateOrderPaymentUseCase {

    private final PaymentServiceClient paymentServiceClient;


    public Mono<PaymentResult> execute(
            UUID orderId,
            UUID customerId,
            BigDecimal price
    ) {
        return paymentServiceClient.initiatePayment(
                orderId,
                customerId,
                price
        );
    }
}