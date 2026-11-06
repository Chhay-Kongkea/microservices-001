package kh.edu.istad.platform.order.domain.usecase;

import kh.edu.istad.platform.order.domain.dto.PayOrderCommand;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import reactor.core.publisher.Mono;

public interface PayOrderUseCase {

    Mono<PayOrderResult> payOrder(PayOrderCommand command);
}
