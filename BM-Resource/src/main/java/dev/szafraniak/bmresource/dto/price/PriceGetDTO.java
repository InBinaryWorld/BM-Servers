package dev.szafraniak.bmresource.dto.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceGetDTO {

    private Long id;

    private BigDecimal net;

    private BigDecimal taxRate;

    private BigDecimal gross;
}
