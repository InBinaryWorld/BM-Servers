package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByKeycloakId(String keycloakId);
}
