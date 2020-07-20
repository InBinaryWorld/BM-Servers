package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.IndividualContactConverter;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.entity.IndividualContact;
import dev.szafraniak.bmresource.repository.entity.IndividualContactRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualContactService extends AbstractCompanyService<IndividualContact, IndividualContactRepository,
        IndividualContactConverter, IndividualContactGetDTO, IndividualContactPostDTO, IndividualContactPutDTO> {

    @Autowired
    public IndividualContactService(IndividualContactConverter converter, IndividualContactRepository repository) {
        super(converter, repository);
    }

}
