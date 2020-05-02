package dev.szafraniak.bmresourceserver.rest.v1.entity.warehouse;

import dev.szafraniak.bmresourceserver.rest.v1.entity.offer.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WarehouseProduct {
    private String id;
    private Product product;
    private BigDecimal quantity;
}
