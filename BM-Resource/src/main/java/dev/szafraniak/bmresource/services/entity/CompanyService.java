package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.CompanyConverter;
import dev.szafraniak.bmresource.dto.entity.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.entity.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.entity.company.CompanyPutDTO;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.services.FileService;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends AbstractService<Company, CompanyRepository,
        CompanyConverter, CompanyGetDTO, CompanyPostDTO, CompanyPutDTO> {

    private UserService userService;

    @Autowired
    public CompanyService(CompanyConverter converter, CompanyRepository repository) {
        super(converter, repository);
    }

    public BmCollection<CompanyGetDTO> getAll() {
        return userService
                .getContextUser()
                .getCompanies().stream()
                .map(converter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    @Override
    public boolean delete(Long entityId) {
        Company entity = repository.findById(entityId).get();
        entity.getInvoices().forEach(invoice ->
                FileService.removeInvoice(invoice.getFileReference())
        );
        return super.delete(entityId);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
