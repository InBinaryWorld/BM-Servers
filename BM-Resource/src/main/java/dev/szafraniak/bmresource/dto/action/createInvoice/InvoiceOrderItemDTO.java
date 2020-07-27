package dev.szafraniak.bmresource.dto.action.createInvoice;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class InvoiceOrderItemDTO {

    @NotNull
    @Pattern(regexp = Regexps.BASE_2_60)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_6)
    private String quantityUnit;

    @NotNull
    @Min(0)
    private BigDecimal quantity;

    @NotNull
    @Min(0)
    private BigDecimal taxRate;

    @NotNull
    @Min(0)
    private BigDecimal netPrice;
}
