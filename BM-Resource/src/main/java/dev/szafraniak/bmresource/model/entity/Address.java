package dev.szafraniak.bmresource.model.entity;

import dev.szafraniak.bmresource.model.shared.AddressInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity implements AddressInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 30)
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
    @Length(max = 5)
    @Pattern(regexp = Regexps.HOUSE_NUMBER)
    private String houseNumber;

    @Length(max = 5)
    @Pattern(regexp = Regexps.HOUSE_NUMBER)
    private String apartmentNumber;

    @Override
    public List<String> getAddressRows() {
        ArrayList<String> addressRows = new ArrayList<>();
        String addressRow = street + " " + houseNumber;
        addressRow += apartmentNumber == null ? "" : "/" + apartmentNumber;
        addressRows.add(addressRow);
        addressRows.add(postalCode + " " + city + ", " + country);
        return addressRows;
    }
}
