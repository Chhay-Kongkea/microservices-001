package kh.edu.istad.platform.payment.config;

import kh.edu.istad.platform.payment.domain.service.PayOrderService;
import kh.edu.istad.platform.payment.domain.usecase.PayOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PaymentBeanConfig {

    @Bean
    public PayOrderUseCase payOrderUseCase() {
        return new PayOrderService();
    }
}
