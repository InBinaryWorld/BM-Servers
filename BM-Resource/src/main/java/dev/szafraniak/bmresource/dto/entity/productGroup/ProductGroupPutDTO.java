package dev.szafraniak.bmresource.dto.entity.productGroup;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ProductGroupPutDTO implements PutDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

//    private List<ProductModel> productModels;

//    private Company company;

}
