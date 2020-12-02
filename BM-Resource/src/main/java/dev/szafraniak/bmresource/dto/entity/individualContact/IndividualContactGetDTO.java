package dev.szafraniak.bmresource.dto.entity.individualContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.entity.contact.ContactGetDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualContactGetDTO extends ContactGetDTO {

    private String firstName;

    private String lastName;

}
