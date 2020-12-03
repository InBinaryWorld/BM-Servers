package dev.szafraniak.bmresource.dto.entity.address;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddressPutDTO implements PutDTOInterface {

//    private Long id;

    @NotNull
    @Length(min = 4, max = 30)
    @Pattern(regexp = Regexps.WORDS)
    private String country;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = Regexps.BASE_4_10)
    private String postalCode;

    @NotNull
    @Length(min = 2, max = 30)
    @Pattern(regexp = Regexps.WORDS)
    private String city;

    @NotNull
    @Length(min = 2, max = 30)
    @Pattern(regexp = Regexps.WORDS)
    private String street;

    @NotNull
    @Pattern(regexp = Regexps.HOUSE_NUMBER)
    private String houseNumber;

    @Pattern(regexp = Regexps.HOUSE_NUMBER)
    private String apartmentNumber;
}
