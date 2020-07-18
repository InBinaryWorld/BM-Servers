package dev.szafraniak.bmresource.dto.serviceModel;

import dev.szafraniak.bmresource.dto.price.PricePostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import dev.szafraniak.bmresource.validator.EnvironmentIds;
import dev.szafraniak.bmresource.validator.VerifyEnvironmentId;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ServiceModelPostDTO {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String name;

    @NotNull
    @VerifyEnvironmentId(source = EnvironmentIds.QUANTITY_UNIT_SERVICE)
    private String quantityUnitId;

    @Valid
    @NotNull
    private PricePostDTO priceSuggestion;

//    private Company company;

}
