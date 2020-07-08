package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {
    List<UserCompany> findAllByOwner_Id(Long ownerId);
}
