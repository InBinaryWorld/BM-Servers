package dev.szafraniak.bmresource.dto.bankAccount;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BankAccountPostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @Pattern(regexp = Regexps.BANK_ACCOUNT)
    private String value;

//    private Company company;
}
