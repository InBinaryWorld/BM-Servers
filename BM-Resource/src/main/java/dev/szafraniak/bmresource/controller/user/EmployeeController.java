package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.services.EmployeeService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/workers")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<EmployeeGetDTO> getAll(@PathVariable Long companyId) {
        return employeeService.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkForCreate(#dto, #companyId)")
    public EmployeeGetDTO create(@PathVariable Long companyId,
                                 @Valid @RequestBody EmployeePostDTO dto) {
        return employeeService.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkEmployee(#companyId, #entityId)")
    public EmployeeGetDTO getEntity(@PathVariable Long companyId,
                                    @PathVariable Long entityId) {
        return employeeService.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkEmployee(#companyId, #entityId)")
    public EmployeeGetDTO update(@PathVariable Long companyId,
                                 @PathVariable Long entityId,
                                 @Valid @RequestBody EmployeePutDTO dto) {
        return employeeService.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductModel(#companyId, #entityId)")
    public void deleteCompany(@PathVariable Long entityId, @PathVariable String companyId) {
        employeeService.delete(entityId);
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}

