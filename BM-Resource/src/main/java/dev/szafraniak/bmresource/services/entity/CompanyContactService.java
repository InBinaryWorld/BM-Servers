package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.CompanyContactConverter;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.entity.CompanyContact;
import dev.szafraniak.bmresource.repository.entity.CompanyContactRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyContactService extends AbstractCompanyService<CompanyContact, CompanyContactRepository,
        CompanyContactConverter, CompanyContactGetDTO, CompanyContactPostDTO, CompanyContactPutDTO> {

    @Autowired
    public CompanyContactService(CompanyContactConverter converter, CompanyContactRepository repository) {
        super(converter, repository);
    }

}
