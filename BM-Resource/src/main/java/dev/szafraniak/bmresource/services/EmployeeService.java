package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.EmployeeConverter;
import dev.szafraniak.bmresource.dto.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.entity.Employee;
import dev.szafraniak.bmresource.repository.EmployeeRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeConverter employeeConverter;
    private EmployeeRepository employeeRepository;


    public BmCollection<EmployeeGetDTO> getAll(Long companyId) {
        return employeeRepository
                .findAllByCompany_Id(companyId).stream()
                .map(employeeConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public EmployeeGetDTO getEntity(Long entityId) {
        Employee employee = employeeRepository.findById(entityId).get();
        return employeeConverter.convertToDTO(employee);
    }

    public EmployeeGetDTO create(EmployeePostDTO dto, Long companyId) {
        Employee employee = employeeConverter.convertFromDTO(dto, companyId);
        Employee saved = employeeRepository.save(employee);
        return employeeConverter.convertToDTO(saved);
    }

    public EmployeeGetDTO update(EmployeePutDTO dto, Long entityId) {
        Employee employee = employeeConverter.convertFromDTO(dto, entityId);
        Employee saved = employeeRepository.save(employee);
        return employeeConverter.convertToDTO(saved);
    }

    public boolean delete(Long entityId) {
        employeeRepository.deleteById(entityId);
        return true;
    }

    @Autowired
    public void setEmployeeConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
