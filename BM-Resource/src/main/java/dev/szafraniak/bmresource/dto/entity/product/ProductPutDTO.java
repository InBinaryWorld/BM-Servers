package dev.szafraniak.bmresource.dto.entity.product;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductPutDTO implements PutDTOInterface {

//    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal quantity;

//    private Long productModelId;

//    private Long warehouseId;

//    private Company company;

}
