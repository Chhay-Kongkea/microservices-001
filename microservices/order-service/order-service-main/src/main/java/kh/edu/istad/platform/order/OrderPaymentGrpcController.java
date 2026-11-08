package kh.edu.istad.platform.order;

import kh.edu.istad.platform.order.domain.dto.PaymentResult;
import kh.edu.istad.platform.order.domain.usercase.InitiateOrderPaymentUseCase;
import kh.edu.istad.platform.order.dto.InitiateOrderPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderPaymentGrpcController {

    private final InitiateOrderPaymentUseCase initiateOrderPaymentUseCase;

    @PostMapping("/payment")
    public Mono<ResponseEntity<PaymentResult>> initiatePayment(
            @RequestBody InitiateOrderPaymentRequest payOrderRequest
            ) {
        return initiateOrderPaymentUseCase.execute(
                payOrderRequest.orderId(),
                payOrderRequest.customerId(),
                payOrderRequest.price()
        ).map(ResponseEntity::ok);
    }
}