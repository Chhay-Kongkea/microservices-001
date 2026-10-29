package kh.edu.istad.platform.order.restapi.integration;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.platform.order.domain.port.out.BusinessValidationPort;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@Component
public class BusinessServiceAdapter implements BusinessValidationPort {

    private final RestClient restClient;

    public BusinessServiceAdapter(
            @LoadBalanced RestClient.Builder builder
    ) {
        this.restClient = builder
                .baseUrl("http://business-service")
                .build();
    }

    @Override
    public void existById(BusinessId id) {
        if (id == null) {
            throw new IllegalArgumentException("Business ID must not be null");
        }

        try {
            restClient.get()
                    .uri("/api/v1/businesses/{businessId}", id.id())
                    .retrieve()
                    .toBodilessEntity();

        } catch (RestClientResponseException exception) {
            if (exception.getStatusCode().value() == 404) {
                throw new IllegalArgumentException(
                        "Business not found: " + id.id(), exception
                );
            }

            throw new IllegalStateException(
                    "Business service returned HTTP "
                            + exception.getStatusCode(),
                    exception
            );

        } catch (RestClientException exception) {
            throw new IllegalStateException(
                    "Unable to connect to Business Service for ID: " + id.id(),
                    exception
            );
        }
    }
}