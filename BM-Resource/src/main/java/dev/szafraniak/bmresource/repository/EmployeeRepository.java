package dev.szafraniak.bmresource.repository;

import dev.szafraniak.bmresource.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByCompany_Id(Long ownerId);
}
