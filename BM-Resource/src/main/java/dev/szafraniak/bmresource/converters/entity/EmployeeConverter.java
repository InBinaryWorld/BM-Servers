package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.entity.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.entity.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.Employee;
import dev.szafraniak.bmresource.model.entity.contact.IndividualContact;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.EmployeeRepository;
import dev.szafraniak.bmresource.repository.entity.IndividualContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements ConverterCompanyInterface<Employee, EmployeeGetDTO, EmployeePostDTO, EmployeePutDTO> {

    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;
    private IndividualContactRepository individualRepository;
    private IndividualContactConverter individualConverter;

    public EmployeeGetDTO convertToDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeGetDTO employeeGetDTO = new EmployeeGetDTO();
        IndividualContact individual = employee.getIndividualContact();
        IndividualContactGetDTO individualDTO = individualConverter.convertToDTO(individual);
        employeeGetDTO.setId(employee.getId());
        employeeGetDTO.setSalary(employee.getSalary());
        employeeGetDTO.setJobPosition(employee.getJobPosition());
        employeeGetDTO.setIndividualContact(individualDTO);
        employeeGetDTO.setFirstEmploymentDate(employee.getFirstEmploymentDate());
        return employeeGetDTO;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Employee convertFromDTO(EmployeePostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        IndividualContact individual = individualRepository.findById(dto.getIndividualId()).get();
        Employee employee = new Employee();
        employee.setSalary(dto.getSalary());
        employee.setCompany(company);
        employee.setJobPosition(dto.getJobPosition());
        employee.setIndividualContact(individual);
        employee.setFirstEmploymentDate(dto.getFirstEmploymentDate());
        return employee;
    }

    public Employee convertFromDTO(EmployeePutDTO dto, Long employeeId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setFirstEmploymentDate(dto.getFirstEmploymentDate());
        employee.setJobPosition(dto.getJobPosition());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    @Autowired
    public void setIndividualConverter(IndividualContactConverter individualConverter) {
        this.individualConverter = individualConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setIndividualRepository(IndividualContactRepository individualRepository) {
        this.individualRepository = individualRepository;
    }
}
