package dev.szafraniak.bmresource.converters.interfaces;

import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.entity.base.BaseEntity;

public interface ConverterCompanyInterface<T extends BaseEntity, G extends GetDTOInterface, P extends PostDTOInterface, S extends PutDTOInterface> {

    G convertToDTO(T entity);

    T convertFromDTO(P dto, Long companyId);

    T convertFromDTO(S dto, Long entityId);

}
