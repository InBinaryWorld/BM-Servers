package dev.szafraniak.bmresource.dto.entity.product;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductPostDTO implements PostDTOInterface {

//    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal quantity;

    @Valid
    @NotNull
    @ManyToOne
    private Long productModelId;

    @Valid
    @NotNull
    @ManyToOne
    private Long warehouseId;

//    private Company company;

}
