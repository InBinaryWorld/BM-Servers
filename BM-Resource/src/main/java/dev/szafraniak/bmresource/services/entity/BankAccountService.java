package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.BankAccountConverter;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountGetDTO;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountPostDTO;
import dev.szafraniak.bmresource.dto.bankAccount.BankAccountPutDTO;
import dev.szafraniak.bmresource.entity.BankAccount;
import dev.szafraniak.bmresource.repository.entity.BankAccountRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
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
