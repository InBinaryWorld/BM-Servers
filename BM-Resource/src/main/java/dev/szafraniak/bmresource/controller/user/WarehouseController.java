package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.warehouse.WarehouseGetDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehousePostDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehousePutDTO;
import dev.szafraniak.bmresource.services.entity.WarehouseService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/warehouses")
public class WarehouseController {

    private WarehouseService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<WarehouseGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public WarehouseGetDTO create(@PathVariable Long companyId,
                                  @Valid @RequestBody WarehousePostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkWarehouse(#companyId, #entityId)")
    public WarehouseGetDTO getEntity(@PathVariable Long companyId,
                                     @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkWarehouse(#companyId, #entityId)")
    public WarehouseGetDTO update(@PathVariable Long companyId,
                                  @PathVariable Long entityId,
                                  @Valid @RequestBody WarehousePutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkFinanceRow(#companyId, #entityId)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long entityId,
                                              @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(WarehouseService service) {
        this.service = service;
    }
}

