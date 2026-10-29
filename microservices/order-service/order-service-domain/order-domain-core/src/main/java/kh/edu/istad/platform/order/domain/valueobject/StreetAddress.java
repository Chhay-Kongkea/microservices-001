package kh.edu.istad.platform.order.domain.valueobject;

public record StreetAddress(
        String street,
        String postalCode,
        String city
) {

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException(
                    "Street must not be blank"
            );
        }

        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException(
                    "Postal code must not be blank"
            );
        }

        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException(
                    "City must not be blank"
            );
        }
    }
}
