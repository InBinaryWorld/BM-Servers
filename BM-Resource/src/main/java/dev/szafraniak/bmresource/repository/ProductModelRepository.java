package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductModelRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAllByCompany_Id(Long companyId);
}
