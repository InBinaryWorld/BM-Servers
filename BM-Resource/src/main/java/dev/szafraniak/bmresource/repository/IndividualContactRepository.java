package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.IndividualContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualContactRepository extends JpaRepository<IndividualContact, Long> {
    List<IndividualContact> findAllByCompany_Id(Long ownerId);
}
