package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.EmployeeConverter;
import dev.szafraniak.bmresource.dto.entity.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.entity.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.entity.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.model.entity.Employee;
import dev.szafraniak.bmresource.repository.entity.EmployeeRepository;
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
