package dev.szafraniak.bmresource.dto.entity.individualContact;

import dev.szafraniak.bmresource.dto.entity.contact.ContactPutDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class IndividualContactPutDTO extends ContactPutDTO {

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String firstName;

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String lastName;

//    private Employee employee;

}
