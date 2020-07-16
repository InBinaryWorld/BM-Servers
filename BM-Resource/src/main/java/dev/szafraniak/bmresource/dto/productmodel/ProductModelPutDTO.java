package dev.szafraniak.bmresource.dto.productmodel;

import dev.szafraniak.bmresource.dto.price.PricePutPostDTO;
import dev.szafraniak.bmresource.dto.shared.BasePostDTO;
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
public class ProductModelPutDTO {

//    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    @Pattern(regexp = Regexps.ALMOST_ALL_CHARACTERS)
    private String name;

    @NotNull
    @VerifyEnvironmentId(source = EnvironmentIds.QUANTITY_UNIT_PRODUCT)
    private String quantityUnitId;

    @Pattern(regexp = Regexps.BARCODE_5_20)
    private String bareCode;

    @Valid
    private PricePutPostDTO priceSuggestion;

    @Valid
    private BasePostDTO productGroup;

//    private List<Product> products = new ArrayList<>();

//    private Company company;
}
