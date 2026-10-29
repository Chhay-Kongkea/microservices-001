package kh.edu.istad.platform.payment.restapi.integration;

import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.port.out.OrderValidationPort;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OrderServiceAdapter implements OrderValidationPort {

    private final RestClient restClient;

    public OrderServiceAdapter(
            @LoadBalanced RestClient.Builder builder
    ) {
        this.restClient = builder
                .baseUrl("http://order-service")
                .build();
    }

    @Override
    public void existById(OrderId id) {
        if (id == null || id.id() == null) {
            throw new IllegalArgumentException(
                    "Order ID must not be null"
            );
        }

        restClient.get()
                .uri("/api/v1/orders/{orderId}", id.id())
                .retrieve()
                .toBodilessEntity();
    }
}