package dev.szafraniak.bmresource.converters.interfaces;

import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.entity.base.BaseEntity;

public interface ConverterInterface<T extends BaseEntity, G extends GetDTOInterface, P extends PostDTOInterface, S extends PutDTOInterface> {

    G convertToDTO(T entity);

    T convertFromDTO(P dto);

    T convertFromDTO(S dto, Long entityId);

}
