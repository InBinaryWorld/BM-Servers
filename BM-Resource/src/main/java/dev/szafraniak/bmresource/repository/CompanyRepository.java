package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByOwner_Id(Long ownerId);
}
