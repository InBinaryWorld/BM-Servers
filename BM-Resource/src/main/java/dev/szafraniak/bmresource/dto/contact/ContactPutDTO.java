package dev.szafraniak.bmresource.dto.contact;

import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ContactPutDTO {

//    private Long id;

    @Valid
    @NotNull
    private AddressPutDTO address;

    @Pattern(regexp = Regexps.PHONE_4_12)
    private String phone;

//    private List<Invoice> invoices;

//    private Company company;

}
