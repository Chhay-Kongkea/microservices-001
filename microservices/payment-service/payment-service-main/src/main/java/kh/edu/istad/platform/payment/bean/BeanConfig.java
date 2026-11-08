package kh.edu.istad.platform.payment.bean;

import kh.edu.istad.platform.payment.domain.service.PaymentDomainService;
import kh.edu.istad.platform.payment.domain.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BeanConfig {

    @Bean
    public PaymentDomainService paymentDomainService(){

        return new PaymentDomainServiceImpl();
    }

    @Bean
    public RestClient.Builder restClientBuilder() {

        return RestClient.builder();
    }
}
