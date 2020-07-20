package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Amount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal net;

    @Min(0)
    @NotNull
    private BigDecimal tax;

    @Min(0)
    @NotNull
    private BigDecimal gross;

}
