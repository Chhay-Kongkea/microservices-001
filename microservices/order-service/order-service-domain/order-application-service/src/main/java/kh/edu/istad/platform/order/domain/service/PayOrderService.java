package kh.edu.istad.platform.order.domain.service;

import kh.edu.istad.platform.order.domain.dto.PayOrderCommand;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import kh.edu.istad.platform.order.domain.port.out.client.PaymentServiceClient;
import kh.edu.istad.platform.order.domain.usecase.PayOrderUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PayOrderService implements PayOrderUseCase {

    private final PaymentServiceClient paymentServiceClient;

    @Override
    public Mono<PayOrderResult> payOrder(PayOrderCommand command) {
        return paymentServiceClient.payOrder(command);
    }
}
