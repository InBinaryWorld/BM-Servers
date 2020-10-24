package dev.szafraniak.bmresource.dto.entity.productmodel;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.entity.price.PricePostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ProductModelPostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_1_6)
    private String quantityUnit;

    @Pattern(regexp = Regexps.BARCODE_5_20)
    private String barcode;

    @Valid
    @NotNull
    private PricePostDTO priceSuggestion;

    @Valid
    private Long productGroupId;

//    private List<Product> products;

//    private Company company;
}
