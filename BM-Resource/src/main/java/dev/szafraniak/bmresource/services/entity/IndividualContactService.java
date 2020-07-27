package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.IndividualContactConverter;
import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.model.entity.IndividualContact;
import dev.szafraniak.bmresource.repository.entity.IndividualContactRepository;
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
