package dev.szafraniak.bmresource.dto.productmodel;

import dev.szafraniak.bmresource.dto.price.PricePutDTO;
import dev.szafraniak.bmresource.dto.shared.BasePostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import dev.szafraniak.bmresource.validator.EnvironmentIds;
import dev.szafraniak.bmresource.validator.VerifyEnvironmentId;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ProductModelPutDTO {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String name;

    @NotNull
    @VerifyEnvironmentId(source = EnvironmentIds.QUANTITY_UNIT_PRODUCT)
    private String quantityUnitId;

    @Pattern(regexp = Regexps.BARCODE_5_20)
    private String bareCode;

    @Valid
    @NotNull
    private PricePutDTO priceSuggestion;

    @Valid
    private BasePostDTO productGroup;

//    private List<Product> products = new ArrayList<>();

//    private Company company;
}
