package kh.edu.istad.platform.order.domain.port.out.client;

import kh.edu.istad.platform.order.domain.dto.PayOrderCommand;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import reactor.core.publisher.Mono;

public interface PaymentServiceClient {

    Mono<PayOrderResult> payOrder(PayOrderCommand command);
}
