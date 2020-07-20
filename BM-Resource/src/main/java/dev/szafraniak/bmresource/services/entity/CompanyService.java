package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.CompanyConverter;
import dev.szafraniak.bmresource.dto.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPutDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.services.AbstractService;
import dev.szafraniak.bmresource.services.UserService;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends AbstractService<Company, CompanyRepository,
        CompanyConverter, CompanyGetDTO, CompanyPostDTO, CompanyPutDTO> {

    protected final UserService userService;

    @Autowired
    public CompanyService(CompanyConverter converter, CompanyRepository repository, UserService userService) {
        super(converter, repository);
        this.userService = userService;
    }

    public BmCollection<CompanyGetDTO> getAll() {
        return userService
                .getOrCreateContextUser()
                .getCompanies().stream()
                .map(converter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

}
