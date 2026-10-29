package kh.edu.istad.platform.order.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class StreetAddressEmbeddable {

    private String street;

    private String postalCode;

    private String city;
}