package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.CompanyContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyContactRepository extends JpaRepository<CompanyContact, Long> {
    List<CompanyContact> findAllByCompany_Id(Long ownerId);
}
