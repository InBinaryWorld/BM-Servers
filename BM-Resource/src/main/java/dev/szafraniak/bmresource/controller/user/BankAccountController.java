package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.bankAccount.BankAccountGetDTO;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountPostDTO;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountPutDTO;
import dev.szafraniak.bmresource.services.entity.BankAccountService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/bank/accounts")
public class BankAccountController {

    private BankAccountService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<BankAccountGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BankAccountGetDTO create(@PathVariable Long companyId,
                                    @Valid @RequestBody BankAccountPostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkBankAccountId(#companyId, #entityId)")
    public BankAccountGetDTO getEntity(@PathVariable Long companyId,
                                       @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkBankAccountId(#companyId, #entityId)")
    public BankAccountGetDTO update(@PathVariable Long companyId,
                                    @PathVariable Long entityId,
                                    @Valid @RequestBody BankAccountPutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkBankAccountId(#companyId, #entityId)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long entityId,
                                              @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(BankAccountService service) {
        this.service = service;
    }
}

