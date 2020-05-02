package dev.szafraniak.bmresourceserver.rest.v1.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Amount {
    private BigDecimal net;
    private BigDecimal tax;
    private BigDecimal gross;
}
