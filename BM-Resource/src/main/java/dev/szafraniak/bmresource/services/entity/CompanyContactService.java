package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.CompanyContactConverter;
import dev.szafraniak.bmresource.dto.entity.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.entity.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.entity.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.model.entity.CompanyContact;
import dev.szafraniak.bmresource.repository.entity.CompanyContactRepository;
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
