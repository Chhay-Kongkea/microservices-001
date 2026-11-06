package kh.edu.istad.platform.order.config;

import kh.edu.istad.platform.order.domain.port.out.client.PaymentServiceClient;
import kh.edu.istad.platform.order.domain.service.PayOrderService;
import kh.edu.istad.platform.order.domain.usecase.PayOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PayOrderConfig {

    @Bean
    public PayOrderUseCase payOrderUseCase(PaymentServiceClient paymentServiceClient) {
        return new PayOrderService(paymentServiceClient);
    }
}
