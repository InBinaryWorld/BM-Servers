package dev.szafraniak.bmresource.controller.entity;

import dev.szafraniak.bmresource.dto.entity.warehouse.WarehouseGetDTO;
import dev.szafraniak.bmresource.dto.entity.warehouse.WarehousePostDTO;
import dev.szafraniak.bmresource.dto.entity.warehouse.WarehousePutDTO;
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
        return service.getAllDTO(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public WarehouseGetDTO create(@PathVariable Long companyId,
                                  @Valid @RequestBody WarehousePostDTO dto) {
        return service.createFromDTO(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkWarehouse(#companyId, #entityId)")
    public WarehouseGetDTO getEntity(@PathVariable Long companyId,
                                     @PathVariable Long entityId) {
        return service.getEntityDTO(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkWarehouse(#companyId, #entityId)")
    public WarehouseGetDTO update(@PathVariable Long companyId,
                                  @PathVariable Long entityId,
                                  @Valid @RequestBody WarehousePutDTO dto) {
        return service.updateFromDTO(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkWarehouse(#companyId, #entityId)")
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

