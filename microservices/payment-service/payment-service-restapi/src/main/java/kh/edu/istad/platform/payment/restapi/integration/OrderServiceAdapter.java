package kh.edu.istad.platform.payment.restapi.integration;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.port.out.OrderValidationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;

@Component
public class OrderServiceAdapter implements OrderValidationPort {


    private final RestClient restClient;

    public OrderServiceAdapter(
            RestClient.Builder builder,
            @Value("${order-service.url}") String customerServiceUrl
    ) {
        this.restClient = builder
                .baseUrl(customerServiceUrl)
                .build();
    }

    @Override
    public void existById(OrderId id) {
        restClient.get()
                .uri("/api/v1/orders/{orderId}", id.id())
                .retrieve()
                .toBodilessEntity();
    }
}
