package dev.szafraniak.bmresource.dto.entity.serviceModel;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.entity.price.PricePostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ServiceModelPostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_1_6)
    private String quantityUnit;

    @Valid
    @NotNull
    private PricePostDTO priceSuggestion;

//    private Company company;

}
