package kh.edu.istad.platform.order.client.config;

import kh.edu.istad.common.grpc.payment.PaymentServiceGrpc;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.ImportGrpcClients;

@Configuration(proxyBeanMethods = false)
@ImportGrpcClients(
        target = "payment",
        types = PaymentServiceGrpc.PaymentServiceBlockingStub.class
)
public class PaymentGrpcClientConfig {
}
