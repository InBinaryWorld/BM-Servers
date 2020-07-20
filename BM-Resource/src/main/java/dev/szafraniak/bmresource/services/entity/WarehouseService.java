package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.WarehouseConverter;
import dev.szafraniak.bmresource.dto.warehouse.WarehouseGetDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehousePostDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehousePutDTO;
import dev.szafraniak.bmresource.entity.Warehouse;
import dev.szafraniak.bmresource.repository.entity.WarehouseRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
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
