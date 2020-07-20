package dev.szafraniak.bmresource.dto.amount;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AmountPutDTO implements PutDTOInterface {

//    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal net;

    @Min(0)
    @NotNull
    private BigDecimal tax;

    @Min(0)
    @NotNull
    private BigDecimal gross;

}
