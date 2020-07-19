package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.Employee;
import dev.szafraniak.bmresource.entity.IndividualContact;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.repository.EmployeeRepository;
import dev.szafraniak.bmresource.repository.IndividualContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

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
