package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Amount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "amount_gen")
    private Long id;

    @NotNull
    private BigDecimal net;

    @NotNull
    private BigDecimal tax;
    
    @NotNull
    private BigDecimal gross;

}
