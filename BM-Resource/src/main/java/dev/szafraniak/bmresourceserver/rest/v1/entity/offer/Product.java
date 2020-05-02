package dev.szafraniak.bmresourceserver.rest.v1.entity.offer;

import lombok.Data;

@Data
public class Product extends Offer {
    private String barCode;
    private ProductGroup group;
}
