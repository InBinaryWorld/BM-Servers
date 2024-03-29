package dev.szafraniak.bmresource.controller.entity;

import dev.szafraniak.bmresource.dto.entity.finantialRow.FinancialRowGetDTO;
import dev.szafraniak.bmresource.dto.entity.finantialRow.FinancialRowPostDTO;
import dev.szafraniak.bmresource.dto.entity.finantialRow.FinancialRowPutDTO;
import dev.szafraniak.bmresource.services.entity.FinancialRowService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/finances")
public class FinancesRowController {

    private FinancialRowService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<FinancialRowGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAllDTO(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public FinancialRowGetDTO create(@PathVariable Long companyId,
                                     @Valid @RequestBody FinancialRowPostDTO dto) {
        return service.createFromDTO(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkFinanceRow(#companyId, #entityId)")
    public FinancialRowGetDTO getEntity(@PathVariable Long companyId,
                                        @PathVariable Long entityId) {
        return service.getEntityDTO(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkFinanceRow(#companyId, #entityId)")
    public FinancialRowGetDTO update(@PathVariable Long companyId,
                                     @PathVariable Long entityId,
                                     @Valid @RequestBody FinancialRowPutDTO dto) {
        return service.updateFromDTO(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkFinanceRow(#companyId, #entityId)")
    public ResponseEntity<Void> delete(@PathVariable Long entityId,
                                       @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(FinancialRowService service) {
        this.service = service;
    }
}

