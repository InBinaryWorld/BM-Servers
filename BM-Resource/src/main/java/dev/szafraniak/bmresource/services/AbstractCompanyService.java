package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import dev.szafraniak.bmresource.repository.CompanyRepositoryInterface;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;

public abstract class AbstractCompanyService<T extends BaseCompanyEntity, R extends CompanyRepositoryInterface<T>,
        C extends ConverterCompanyInterface<T, G, P, S>, G extends GetDTOInterface, P extends PostDTOInterface, S extends PutDTOInterface> {

    protected final C converter;
    protected final R repository;

    public AbstractCompanyService(C converter, R repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public BmCollection<G> getAll(Long companyId) {
        return repository.findAllByCompany_Id(companyId)
                .stream().map(converter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public G getEntity(Long entityId) {
        T entity = repository.findById(entityId).get();
        return converter.convertToDTO(entity);
    }

    public G create(P dto, Long companyId) {
        T entity = converter.convertFromDTO(dto, companyId);
        T saved = repository.save(entity);
        return converter.convertToDTO(saved);
    }

    public G update(S dto, Long entityId) {
        T entity = converter.convertFromDTO(dto, entityId);
        T saved = repository.save(entity);
        return converter.convertToDTO(saved);
    }

    public boolean delete(Long entityId) {
        repository.deleteById(entityId);
        return !repository.existsById(entityId);
    }

}
