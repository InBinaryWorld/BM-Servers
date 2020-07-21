package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountGetDTO;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountPostDTO;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountPutDTO;
import dev.szafraniak.bmresource.entity.BankAccount;
import dev.szafraniak.bmresource.entity.Company;
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
        dto.setValue(entity.getValue());
        return dto;
    }

    @Override
    public BankAccount convertFromDTO(BankAccountPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        BankAccount account = new BankAccount();
        account.setName(dto.getName());
        account.setValue(dto.getValue());
        account.setCompany(company);
        return account;
    }

    @Override
    public BankAccount convertFromDTO(BankAccountPutDTO dto, Long entityId) {
        if (dto == null) {
            return null;
        }
        BankAccount account = bankAccountRepository.findById(entityId).get();
        account.setName(dto.getName());
        account.setValue(dto.getValue());
        return account;
    }

    @Autowired
    public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
