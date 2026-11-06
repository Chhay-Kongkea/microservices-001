package kh.edu.istad.platform.order.client.adapter;

import kh.edu.istad.common.grpc.payment.PayOrderRequest;
import kh.edu.istad.common.grpc.payment.PayOrderResponse;
import kh.edu.istad.common.grpc.payment.PaymentServiceGrpc;
import kh.edu.istad.platform.order.domain.dto.PayOrderCommand;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import kh.edu.istad.platform.order.domain.port.out.client.PaymentServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class PaymentGrpcClientAdapter implements PaymentServiceClient {

    private final PaymentServiceGrpc.PaymentServiceBlockingStub paymentStub;

    @Override
    public Mono<PayOrderResult> payOrder(PayOrderCommand command) {
        return Mono.fromCallable(() -> {
            System.out.println("pay{}"+ command);
            PayOrderRequest request = PayOrderRequest.newBuilder()
                    .setOrderId(command.orderId())
                    .setCustomerId(command.customerId())
                    .setAmount(command.price())
                    .build();

            PayOrderResponse response = paymentStub.payOrder(request);

            return new PayOrderResult(
                    response.getPaymentId(),
                    response.getOrderId(),
                    response.getSuccessful(),
                    response.getMessage()
            );
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
