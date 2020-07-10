package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
