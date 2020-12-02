package dev.szafraniak.bmresource.dto.entity.productmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModelGetDTO implements GetDTOInterface {

    private Long id;

    private String name;

    private String quantityUnit;

    private String barcode;

    private PriceGetDTO priceSuggestion;

//    private List<Product> products;

//    private Company company;
}
