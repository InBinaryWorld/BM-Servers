package dev.szafraniak.bmresourceserver.rest.v1.entity.offer;

import dev.szafraniak.bmresourceserver.rest.v1.entity.Price;
import lombok.Data;

@Data
public class Offer {
    private String id;
    private String name;
    private String unit;
    private Price price;
}
