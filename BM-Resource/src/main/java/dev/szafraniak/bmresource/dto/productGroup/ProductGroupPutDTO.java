package dev.szafraniak.bmresource.dto.productGroup;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ProductGroupPutDTO {

//    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 50)
    @Pattern(regexp = Regexps.ALMOST_ALL_CHARACTERS)
    private String name;

//    private List<ProductModel> productModels;

//    private Company company;

}
