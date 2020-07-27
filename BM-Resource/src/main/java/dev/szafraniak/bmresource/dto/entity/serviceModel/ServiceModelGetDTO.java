package dev.szafraniak.bmresource.dto.entity.serviceModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.price.PriceGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceModelGetDTO implements GetDTOInterface {

    private Long id;

    private String name;

    private String quantityUnit;

    private PriceGetDTO priceSuggestion;

//    private Company company;

}
