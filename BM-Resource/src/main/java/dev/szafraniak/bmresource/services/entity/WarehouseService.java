package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.WarehouseConverter;
import dev.szafraniak.bmresource.dto.entity.warehouse.WarehouseGetDTO;
import dev.szafraniak.bmresource.dto.entity.warehouse.WarehousePostDTO;
import dev.szafraniak.bmresource.dto.entity.warehouse.WarehousePutDTO;
import dev.szafraniak.bmresource.model.entity.Warehouse;
import dev.szafraniak.bmresource.repository.entity.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends AbstractCompanyService<Warehouse, WarehouseRepository,
        WarehouseConverter, WarehouseGetDTO, WarehousePostDTO, WarehousePutDTO> {

    @Autowired
    public WarehouseService(WarehouseConverter converter, WarehouseRepository repository) {
        super(converter, repository);
    }
}
