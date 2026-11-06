package kh.edu.istad.platform.order;

import kh.edu.istad.common.grpc.payment.PaymentServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.grpc.client.ImportGrpcClients;

@SpringBootApplication
@ImportGrpcClients(
        target = "payment",
        types = PaymentServiceGrpc.PaymentServiceBlockingStub.class
)
public class OrderApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationService.class, args);
    }

}