package dev.szafraniak.bmresource.dto.address;

import lombok.Data;

@Data
public class AddressGetDTO {

    private Long id;

    private String country;

    private String postalCode;

    private String city;

    private String addressLine1;

    private String addressLine2;
}
