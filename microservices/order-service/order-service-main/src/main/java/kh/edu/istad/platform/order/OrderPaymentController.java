package kh.edu.istad.platform.order;

import kh.edu.istad.platform.order.domain.dto.PayOrderCommand;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import kh.edu.istad.platform.order.domain.usecase.PayOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderPaymentController {

    private final PayOrderUseCase payOrderUseCase;

    @PostMapping("/pay")
    public Mono<PayOrderResult> payOrder(@RequestBody PayOrderCommand command) {
        return payOrderUseCase.payOrder(command);
    }
}
