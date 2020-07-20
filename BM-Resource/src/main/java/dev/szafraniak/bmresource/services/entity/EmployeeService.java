package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.EmployeeConverter;
import dev.szafraniak.bmresource.dto.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.entity.Employee;
import dev.szafraniak.bmresource.repository.entity.EmployeeRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends AbstractCompanyService<Employee, EmployeeRepository,
        EmployeeConverter, EmployeeGetDTO, EmployeePostDTO, EmployeePutDTO> {

    @Autowired
    public EmployeeService(EmployeeConverter converter, EmployeeRepository repository) {
        super(converter, repository);
    }
}
