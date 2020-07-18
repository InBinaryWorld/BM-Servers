package dev.szafraniak.bmresource.dto.amount;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmountGetDTO {

    private Long id;

    private BigDecimal net;

    private BigDecimal tax;

    private BigDecimal gross;

}
