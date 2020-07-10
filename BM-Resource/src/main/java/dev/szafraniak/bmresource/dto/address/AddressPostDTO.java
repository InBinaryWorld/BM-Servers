package dev.szafraniak.bmresource.dto.address;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddressPostDTO {

//    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String country;

    @NotNull
    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String postalCode;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String city;

    @NotNull
    @Pattern(regexp = "[^\\t\\n\\r\\f\\x0B]{1,45}")
    private String addressLine1;

    @Pattern(regexp = "[^\\t\\n\\r\\f\\x0B]{1,45}")
    private String addressLine2;
}
