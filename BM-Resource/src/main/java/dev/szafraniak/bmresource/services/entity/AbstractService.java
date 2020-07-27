package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.ConverterInterface;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.model.entity.BaseEntity;
import dev.szafraniak.bmresource.repository.RepositoryInterface;

public abstract class AbstractService<T extends BaseEntity, R extends RepositoryInterface<T>,
        C extends ConverterInterface<T, G, P, S>, G extends GetDTOInterface, P extends PostDTOInterface, S extends PutDTOInterface> {

    protected final C converter;
    protected final R repository;

    public AbstractService(C converter, R repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public G getEntityDTO(Long entityId) {
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        T entity = repository.findById(entityId).get();
        return converter.convertToDTO(entity);
    }

    public G createFromDTO(P dto) {
        T entity = converter.convertFromDTO(dto);
        T saved = repository.save(entity);
        return converter.convertToDTO(saved);
    }

    public G updateFromDTO(S dto, Long entityId) {
        T entity = converter.convertFromDTO(dto, entityId);
        T saved = repository.save(entity);
        return converter.convertToDTO(saved);
    }

    public boolean delete(Long entityId) {
        repository.deleteById(entityId);
        return !repository.existsById(entityId);
    }

    public T getEntity(Long entityId) {
        //noinspection OptionalGetWithoutIsPresent
        return repository.findById(entityId).get();
    }

}
