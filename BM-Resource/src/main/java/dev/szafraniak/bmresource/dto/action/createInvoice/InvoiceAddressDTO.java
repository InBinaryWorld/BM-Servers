package dev.szafraniak.bmresource.dto.action.createInvoice;

import dev.szafraniak.bmresource.model.shared.AddressInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class InvoiceAddressDTO implements AddressInterface {

    @NotNull
    @Length(min = 4, max = 25)
    @Pattern(regexp = Regexps.WORDS)
    private String country;

    @NotNull
    @Pattern(regexp = Regexps.POSTAL_CODE)
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
