package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.employee.EmployeeGetDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.employee.EmployeePutDTO;
import dev.szafraniak.bmresource.services.entity.EmployeeService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/workers")
public class EmployeeController {

    private EmployeeService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<EmployeeGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkForCreate(#dto, #companyId)")
    public EmployeeGetDTO create(@PathVariable Long companyId,
                                 @Valid @RequestBody EmployeePostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkEmployee(#companyId, #entityId)")
    public EmployeeGetDTO getEntity(@PathVariable Long companyId,
                                    @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkEmployee(#companyId, #entityId)")
    public EmployeeGetDTO update(@PathVariable Long companyId,
                                 @PathVariable Long entityId,
                                 @Valid @RequestBody EmployeePutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkEmployee(#companyId, #entityId)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long entityId, @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(EmployeeService service) {
        this.service = service;
    }
}

