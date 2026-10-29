package kh.edu.istad.platform.order.restapi.integration;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.platform.order.domain.port.out.CustomerValidationPort;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerServiceAdapter implements CustomerValidationPort {

    private final RestClient restClient;

    public CustomerServiceAdapter(
            @LoadBalanced RestClient.Builder builder
    ) {
        this.restClient = builder
                .baseUrl("http://customer-service")
                .build();
    }

    @Override
    public void existById(CustomerId id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Customer ID must not be null"
            );
        }

        restClient.get()
                .uri("/api/v1/customers/{customerId}", id.id())
                .retrieve()
                .toBodilessEntity();
    }
}