package dev.szafraniak.bmresource.dto.companyContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.contact.ContactGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyContactGetDTO extends ContactGetDTO {

    private String name;

    private String taxIdentityNumber;

}
