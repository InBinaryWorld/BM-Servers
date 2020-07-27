package dev.szafraniak.bmresource.dto.entity.contact;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.entity.address.AddressPostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ContactPostDTO implements PostDTOInterface {

//    private Long id;

    @Valid
    @NotNull
    private AddressPostDTO address;

    @Pattern(regexp = Regexps.PHONE_4_12)
    private String phone;

//    private List<Invoice> invoices;

//    private Company company;

}
