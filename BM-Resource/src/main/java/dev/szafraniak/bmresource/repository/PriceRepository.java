package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
