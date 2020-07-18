package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceModelRepository extends JpaRepository<ServiceModel, Long> {
    List<ServiceModel> findAllByCompany_Id(Long companyId);
}
