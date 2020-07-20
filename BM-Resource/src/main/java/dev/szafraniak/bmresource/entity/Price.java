package dev.szafraniak.bmresource.entity;

import com.sun.istack.NotNull;
import dev.szafraniak.bmresource.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Entity
public class Price extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal net;

    @Min(0)
    @NotNull
    private BigDecimal taxRate;

    @Min(0)
    @NotNull
    private BigDecimal gross;

}
