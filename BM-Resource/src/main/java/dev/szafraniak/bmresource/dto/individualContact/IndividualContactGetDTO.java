package dev.szafraniak.bmresource.dto.individualContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.contact.ContactGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualContactGetDTO extends ContactGetDTO {

    private String firstName;

    private String lastName;

//    private Employee employee;

}
