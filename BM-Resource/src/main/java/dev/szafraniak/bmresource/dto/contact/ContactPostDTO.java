package dev.szafraniak.bmresource.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.szafraniak.bmresource.dto.address.AddressPostDTO;
import dev.szafraniak.bmresource.entity.Invoice;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public abstract class ContactPostDTO {

//    private Long id;

    @Valid
    @NotNull
    private AddressPostDTO address;

    @Pattern(regexp = Regexps.PHONE_4_12)
    private String phone;

    @JsonIgnore
    private List<Invoice> invoice = new ArrayList<>();

//    private Company company;

}
