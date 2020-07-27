package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.BankAccountConverter;
import dev.szafraniak.bmresource.dto.entity.bankAccount.BankAccountGetDTO;
import dev.szafraniak.bmresource.dto.entity.bankAccount.BankAccountPostDTO;
import dev.szafraniak.bmresource.dto.entity.bankAccount.BankAccountPutDTO;
import dev.szafraniak.bmresource.model.entity.BankAccount;
import dev.szafraniak.bmresource.repository.entity.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService extends AbstractCompanyService<BankAccount, BankAccountRepository,
        BankAccountConverter, BankAccountGetDTO, BankAccountPostDTO, BankAccountPutDTO> {

    @Autowired
    public BankAccountService(BankAccountConverter converter, BankAccountRepository repository) {
        super(converter, repository);
    }
}
