package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.model.entity.BaseEntity;

public interface ConverterInterface<T extends BaseEntity, G extends GetDTOInterface, P extends PostDTOInterface, S extends PutDTOInterface> {

    G convertToDTO(T entity);

    T convertFromDTO(P dto);

    T convertFromDTO(S dto, Long entityId);

}
