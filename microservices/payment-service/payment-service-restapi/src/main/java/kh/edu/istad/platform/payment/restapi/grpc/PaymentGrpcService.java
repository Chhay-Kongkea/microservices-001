package kh.edu.istad.platform.payment.restapi.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import kh.edu.istad.common.grpc.payment.PayOrderRequest;
import kh.edu.istad.common.grpc.payment.PayOrderResponse;
import kh.edu.istad.common.grpc.payment.PaymentServiceGrpc;

import kh.edu.istad.common.grpc.payment.PaymentStatusGrpc;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentResult;
import kh.edu.istad.platform.payment.domain.usecase.InitiatePaymentUseCase;

import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.math.BigDecimal;
import java.util.UUID;


@Slf4j
@GrpcService
@RequiredArgsConstructor
public class PaymentGrpcService
        extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final InitiatePaymentUseCase initiatePaymentUseCase;

    @Override
    public void payOrder(
            PayOrderRequest request,
            StreamObserver<PayOrderResponse> responseObserver
    ) {
        try {
            UUID orderId = UUID.fromString(request.getOrderId());
            UUID customerId = UUID.fromString(request.getCustomerId());

            BigDecimal price = BigDecimal.valueOf(
                    request.getPrice(),
                    2
            );


            if (price.signum() <= 0) {
                throw new IllegalArgumentException(
                        "Payment amount must be positive"
                );
            }

            InitiatePaymentCommand command =
                    new InitiatePaymentCommand(
                            orderId,
                            customerId,
                            price
                    );


            InitiatePaymentResult result = initiatePaymentUseCase.execute(command);


            PayOrderResponse response = PayOrderResponse.newBuilder()
                    .setPaymentId(result.paymentId().toString())
                    .setOrderId(result.orderId().toString())
                    .setPrice(request.getPrice())
                    .setStatus(mapPaymentStatus(result.paymentStatus()))
                    .setMessage("Payment processed successfully")
                    .build();


            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            log.warn("Invalid payment request: {}", e.getMessage());

            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );

        } catch (Exception e) {
            log.error("Payment processing failed", e);

            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Payment processing failed")
                            .asRuntimeException()
            );
        }
    }

    private PaymentStatusGrpc mapPaymentStatus(PaymentStatus status
    ) {
        return switch (status) {
            case COMPLETED -> PaymentStatusGrpc.PAYMENT_STATUS_COMPLETED;

            case CANCELED -> PaymentStatusGrpc.PAYMENT_STATUS_CANCELED;

            case FAILED -> PaymentStatusGrpc.PAYMENT_STATUS_FAILED;
        };
    }
}