package dev.szafraniak.bmresource.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCompanyEntity extends BaseEntity {
    protected Company company;

}

