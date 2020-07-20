package dev.szafraniak.bmresource.entity.base;

import dev.szafraniak.bmresource.entity.Company;
import lombok.Data;

@Data
public abstract class BaseCompanyEntity extends BaseEntity {

    protected Company company;
}
