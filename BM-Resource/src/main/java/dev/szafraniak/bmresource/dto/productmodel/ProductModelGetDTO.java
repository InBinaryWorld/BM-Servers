package dev.szafraniak.bmresource.dto.productmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModelGetDTO {

    private Long id;

    private String name;

    private String quantityUnitId;

    private String bareCode;

    private PriceGetDTO priceSuggestion;

    private BaseGetDTO productGroup;

//    private List<Product> products;

//    private Company company;
}
