package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryInterface<T extends BaseEntity> extends JpaRepository<T, Long> {
}
