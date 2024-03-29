package dev.szafraniak.bmresource.dto.entity.finantialRow;

import dev.szafraniak.bmresource.annotations.nullOrNotBlank.NullOrNotBlank;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class FinancialRowPostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String title;

    @NotNull
    private OffsetDateTime eventDate;

    @NullOrNotBlank
    @Pattern(regexp = Regexps.BASE_2_240)
    private String description;

    @NotNull
    private BigDecimal amountChange;

//    private Company company;

}
