package dev.szafraniak.bmresource.dto.serviceModel;

import dev.szafraniak.bmresource.dto.price.PricePostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import dev.szafraniak.bmresource.validator.EnvironmentIds;
import dev.szafraniak.bmresource.validator.VerifyEnvironmentId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ServiceModelPostDTO {

//    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    @Pattern(regexp = Regexps.ALMOST_ALL_CHARACTERS)
    private String name;

    @NotNull
    @VerifyEnvironmentId(source = EnvironmentIds.QUANTITY_UNIT_SERVICE)
    private String quantityUnitId;

    @Valid
    @NotNull
    private PricePostDTO priceSuggestion;

//    private Company company;

}
