package dev.szafraniak.bmresource.dto.serviceModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.price.PriceGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceModelGetDTO {

    private Long id;

    private String name;

    private String quantityUnitId;

    private PriceGetDTO priceSuggestion;

//    private Company company;

}
