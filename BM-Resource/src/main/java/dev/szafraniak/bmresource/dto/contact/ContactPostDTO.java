package dev.szafraniak.bmresource.dto.contact;

import dev.szafraniak.bmresource.dto.address.AddressPostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ContactPostDTO {

//    private Long id;

    @Valid
    @NotNull
    private AddressPostDTO address;

    @Pattern(regexp = Regexps.PHONE_4_12)
    private String phone;

//    private List<Invoice> invoices;

//    private Company company;

}
