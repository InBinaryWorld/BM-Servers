package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.bankAccount.BankAccountGetDTO;
import dev.szafraniak.bmresource.dto.entity.bankAccount.BankAccountPostDTO;
import dev.szafraniak.bmresource.dto.entity.bankAccount.BankAccountPutDTO;
import dev.szafraniak.bmresource.model.entity.BankAccount;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.repository.entity.BankAccountRepository;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankAccountConverter implements ConverterCompanyInterface<BankAccount,
        BankAccountGetDTO, BankAccountPostDTO, BankAccountPutDTO> {

    private CompanyRepository companyRepository;
    private BankAccountRepository bankAccountRepository;


    @Override
    public BankAccountGetDTO convertToDTO(BankAccount entity) {
        if (entity == null) {
            return null;
        }
        BankAccountGetDTO dto = new BankAccountGetDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumber(entity.getValue());
        return dto;
    }

    @Override
    public BankAccount convertFromDTO(BankAccountPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Company company = companyRepository.findById(companyId).get();
        BankAccount account = new BankAccount();
        account.setName(dto.getName());
        account.setValue(dto.getNumber());
        account.setCompany(company);
        return account;
    }

    @Override
    public BankAccount convertFromDTO(BankAccountPutDTO dto, Long entityId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        BankAccount account = bankAccountRepository.findById(entityId).get();
        account.setName(dto.getName());
        account.setValue(dto.getNumber());
        return account;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
}
