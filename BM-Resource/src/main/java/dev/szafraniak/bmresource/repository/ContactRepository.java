package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByCompany_Id(Long ownerId);
}
