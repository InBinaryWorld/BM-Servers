package dev.szafraniak.bmresource.repository.entity;

import dev.szafraniak.bmresource.model.entity.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByCompany_Id(Long ownerId);
}
