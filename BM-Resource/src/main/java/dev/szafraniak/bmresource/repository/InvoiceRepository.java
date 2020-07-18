package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByCompany_Id(Long ownerId);
}
