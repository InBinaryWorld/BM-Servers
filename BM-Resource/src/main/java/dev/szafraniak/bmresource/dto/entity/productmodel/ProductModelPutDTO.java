package dev.szafraniak.bmresource.dto.entity.productmodel;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.dto.entity.price.PricePutDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ProductModelPutDTO implements PutDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_6)
    private String quantityUnit;

    @Pattern(regexp = Regexps.BARCODE_5_20)
    private String bareCode;

    @Valid
    @NotNull
    private PricePutDTO priceSuggestion;

    @Valid
    private Long productGroupId;

//    private List<Product> products = new ArrayList<>();

//    private Company company;
}
