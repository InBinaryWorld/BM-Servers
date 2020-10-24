package dev.szafraniak.bmresource.dto.entity.amount;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.config.BaseEnvironment;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmountGetDTO implements GetDTOInterface {

    private Long id;

    private BigDecimal net;

    private BigDecimal tax;

    private BigDecimal gross;
    
    private String currency = BaseEnvironment.CURRENCY;

}
