package dev.szafraniak.bmresource.dto.entity.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceGetDTO implements GetDTOInterface {

    private Long id;

    private BigDecimal net;

    private BigDecimal taxRate;

    private BigDecimal gross;
}
