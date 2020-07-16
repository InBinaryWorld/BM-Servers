package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    List<ProductGroup> findAllByCompany_Id(Long companyId);
}
