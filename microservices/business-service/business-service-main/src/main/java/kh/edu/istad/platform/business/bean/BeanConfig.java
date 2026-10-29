package kh.edu.istad.platform.business.bean;




import kh.edu.platformbusiness.domain.service.BusinessDomainService;
import kh.edu.platformbusiness.domain.service.BusinessDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BeanConfig {

    @Bean
    public BusinessDomainService businessDomainService(){

        return new BusinessDomainServiceImpl();
    }

    @Bean
    public RestClient.Builder restClientBuilder() {

        return RestClient.builder();
    }


}
