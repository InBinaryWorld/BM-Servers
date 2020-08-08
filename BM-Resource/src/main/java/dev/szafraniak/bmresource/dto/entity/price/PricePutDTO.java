package dev.szafraniak.bmresource.dto.entity.price;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import dev.szafraniak.bmresource.dto.PutDTOInterface;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class PricePutDTO implements PutDTOInterface {

//    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal net;

    @Min(0)
    @NotNull
    private BigDecimal taxRate;

    @JsonIgnore
    private BigDecimal gross;
}
