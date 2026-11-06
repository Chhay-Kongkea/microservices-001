package kh.edu.istad.platform.payment.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import kh.edu.istad.common.grpc.payment.PayOrderRequest;
import kh.edu.istad.common.grpc.payment.PayOrderResponse;
import kh.edu.istad.common.grpc.payment.PaymentServiceGrpc;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.entity.PaymentStatus;
import kh.edu.istad.platform.payment.domain.usecase.PayOrderUseCase;
import org.springframework.grpc.server.service.GrpcService;

import java.math.BigDecimal;

@GrpcService
public class PaymentGrpcService extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final PayOrderUseCase payOrderUseCase;

    public PaymentGrpcService(PayOrderUseCase payOrderUseCase) {
        this.payOrderUseCase = payOrderUseCase;
    }

    @Override
    public void payOrder(
            PayOrderRequest request,
            StreamObserver<PayOrderResponse> responseObserver
    ) {
        try {
            Payment payment = payOrderUseCase.payOrder(
                    request.getOrderId(),
                    request.getCustomerId(),
                    new BigDecimal(request.getAmount())
            );

            PayOrderResponse response = PayOrderResponse.newBuilder()
                    .setPaymentId(payment.paymentId())
                    .setOrderId(payment.orderId())
                    .setSuccessful(payment.status() == PaymentStatus.COMPLETED)
                    .setMessage("Payment completed successfully (demo)")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException exception) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription(exception.getMessage())
                            .asRuntimeException()
            );
        }
    }
}
