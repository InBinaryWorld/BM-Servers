package dev.szafraniak.bmresource.dto.price;

import com.sun.istack.NotNull;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class PricePostDTO implements PostDTOInterface {

//    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal net;

    @Min(0)
    @NotNull
    private BigDecimal taxRate;

    @Min(0)
    @NotNull
    private BigDecimal gross;
}
