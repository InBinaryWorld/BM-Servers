package dev.szafraniak.bmresource.dto.entity.companyContact;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.entity.contact.ContactGetDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyContactGetDTO extends ContactGetDTO {

    private String name;

    private String taxIdentityNumber;

}
