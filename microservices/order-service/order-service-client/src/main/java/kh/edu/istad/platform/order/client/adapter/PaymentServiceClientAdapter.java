package kh.edu.istad.platform.order.client.adapter;

import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.ClientResponseObserver;
import kh.edu.istad.common.grpc.payment.PayOrderRequest;
import kh.edu.istad.common.grpc.payment.PayOrderResponse;
import kh.edu.istad.common.grpc.payment.PaymentServiceGrpc;
import kh.edu.istad.common.grpc.payment.PaymentStatusGrpc;
import kh.edu.istad.platform.order.domain.dto.PaymentResult;
import kh.edu.istad.platform.order.domain.port.out.client.PaymentServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class PaymentServiceClientAdapter implements PaymentServiceClient {

    private static final long DEADLINE_SECONDS = 3;
    private static final int CURRENCY_SCALE = 2;


    private final PaymentServiceGrpc.PaymentServiceStub paymentServiceStub;

    @Override
    public Mono<PaymentResult> initiatePayment(
            UUID orderId,
            UUID customerId,
            BigDecimal price
    ) {
        return Mono.create(sink -> {

            final long amountMinorUnits;

            try {
                if (orderId == null || customerId == null) {
                    sink.error(new IllegalArgumentException(
                            "Order ID and Customer ID are required"
                    ));
                    return;
                }

                if (price == null || price.signum() <= 0) {
                    sink.error(new IllegalArgumentException(
                            "Payment amount must be positive"
                    ));
                    return;
                }



            } catch (ArithmeticException e) {
                sink.error(new IllegalArgumentException(
                        "Payment amount must have at most 2 decimal places",
                        e
                ));
                return;
            }


            PayOrderRequest request = PayOrderRequest.newBuilder()
                    .setOrderId(orderId.toString())
                    .setCustomerId(customerId.toString())
                    .setPrice(price.longValueExact())
                    .build();

            AtomicReference<ClientCallStreamObserver<PayOrderRequest>>
                    callReference = new AtomicReference<>();

            AtomicBoolean cancellationRequested = new AtomicBoolean(false);

            sink.onCancel(() -> {

                cancellationRequested.set(true);

                ClientCallStreamObserver<PayOrderRequest> call =
                        callReference.get();

                if (call != null) {
                    call.cancel("Reactor subscription cancelled", null);
                }
            });

            PaymentServiceGrpc.PaymentServiceStub deadlineStub =
                    paymentServiceStub.withDeadlineAfter(
                            DEADLINE_SECONDS,
                            TimeUnit.SECONDS
                    );

            deadlineStub.payOrder(
                    request,
                    new ClientResponseObserver<PayOrderRequest, PayOrderResponse>() {

                        @Override
                        public void beforeStart(
                                ClientCallStreamObserver<PayOrderRequest> requestStream
                        ) {
                            callReference.set(requestStream);
                            if (cancellationRequested.get()) {
                                requestStream.cancel("Reactor subscription cancelled", null);
                            }
                        }

                        @Override
                        public void onNext(PayOrderResponse response) {

                            PaymentResult result = new PaymentResult(
                                    response.getPaymentId(),
                                    UUID.fromString(response.getOrderId()),
                                    BigDecimal.valueOf(
                                            response.getPrice(),
                                            CURRENCY_SCALE
                                    ),
                                    response.getStatus()
                                            == PaymentStatusGrpc.PAYMENT_STATUS_COMPLETED,
                                    response.getMessage()
                            );

                            sink.success(result);
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            sink.error(throwable);
                        }

                        @Override
                        public void onCompleted() {

                        }
                    }
            );
        });
    }
}