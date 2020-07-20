package dev.szafraniak.bmresource.dto.individualContact;

import dev.szafraniak.bmresource.dto.contact.ContactPostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class IndividualContactPostDTO extends ContactPostDTO {

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String firstName;

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String lastName;

//    private Employee employee;

}
