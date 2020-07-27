package dev.szafraniak.bmresource.dto.entity.productGroup;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGroupGetDTO implements GetDTOInterface {

    private Long id;

    private String name;

    private List<ProductModelGetDTO> productModels;

//    private Company company;

}
