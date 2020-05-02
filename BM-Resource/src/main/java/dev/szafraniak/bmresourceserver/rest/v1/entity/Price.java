package dev.szafraniak.bmresourceserver.rest.v1.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {
    private BigDecimal net;
    private BigDecimal taxRate;
    private BigDecimal gross;
}
