package dev.szafraniak.bmresource.dto.bankAccount;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountGetDTO implements GetDTOInterface {

    private Long id;

    private String name;

    private String value;

//    private Company company;
}
