package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.model.entity.BaseCompanyEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CompanyRepositoryInterface<T extends BaseCompanyEntity> extends RepositoryInterface<T> {
    List<T> findAllByCompany_Id(Long ownerId);
}
