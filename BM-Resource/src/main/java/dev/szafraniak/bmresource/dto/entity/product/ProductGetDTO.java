package dev.szafraniak.bmresource.dto.entity.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGetDTO implements GetDTOInterface {

//    private Long id;

    private BigDecimal quantity;

    private ProductModelGetDTO productModel;

    private BaseGetDTO warehouse;

//    private Company company;

}
