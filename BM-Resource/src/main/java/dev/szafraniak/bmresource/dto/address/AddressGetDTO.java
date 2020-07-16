package dev.szafraniak.bmresource.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressGetDTO {

    private Long id;

    private String country;

    private String postalCode;

    private String city;

    private String street;

    private String houseNumber;

    private String apartmentNumber;
}
