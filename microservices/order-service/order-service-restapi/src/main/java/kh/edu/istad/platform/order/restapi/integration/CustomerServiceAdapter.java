package kh.edu.istad.platform.order.restapi.integration;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.platform.order.domain.port.out.CustomerValidationPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerServiceAdapter implements CustomerValidationPort {

    private final RestClient restClient;

    public CustomerServiceAdapter(
            RestClient.Builder builder,
            @Value("${customer-service.url}") String customerServiceUrl
    ) {
        this.restClient = builder
                .baseUrl(customerServiceUrl)
                .build();
    }

    @Override
    public void existById(CustomerId id) {
        restClient.get()
                .uri("/api/v1/customers/{customerId}", id.id())
                .retrieve()
                .toBodilessEntity();
    }
}