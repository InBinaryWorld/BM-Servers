package dev.szafraniak.bmresourceserver.rest.v1.entity.invoice;

import dev.szafraniak.bmresourceserver.rest.v1.entity.Amount;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItem {
    private String id;
    private Invoice invoice;
    private String name;
    private String unit;
    private BigDecimal quantity;
    private Amount amount;
}
