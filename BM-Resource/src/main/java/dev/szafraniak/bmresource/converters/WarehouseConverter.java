package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.product.ProductGetDTO;
import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehouseGetDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehousePostDTO;
import dev.szafraniak.bmresource.dto.warehouse.WarehousePutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.Warehouse;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WarehouseConverter implements ConverterCompanyInterface<Warehouse, WarehouseGetDTO, WarehousePostDTO, WarehousePutDTO> {

    private ProductConverter productConverter;
    private AddressConverter addressConverter;
    private CompanyRepository companyRepository;
    private WarehouseRepository warehouseRepository;

    @Override
    public WarehouseGetDTO convertToDTO(Warehouse entity) {
        if (entity == null) {
            return null;
        }
        AddressGetDTO address = addressConverter.convertToDTO(entity.getAddress());
        List<ProductGetDTO> products = entity.getProducts().stream()
                .map(productConverter::convertToDTO)
                .collect(Collectors.toList());

        WarehouseGetDTO dto = new WarehouseGetDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(address);
        dto.setProducts(products);
        return dto;
    }

    public BaseGetDTO convertToBaseDTO(Warehouse warehouse) {
        BaseGetDTO dto = new BaseGetDTO();
        dto.setName(warehouse.getName());
        dto.setId(warehouse.getId());
        return dto;
    }

    @Override
    public Warehouse convertFromDTO(WarehousePostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        Address address = addressConverter.convertFromDTO(dto.getAddress());
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setAddress(address);
        warehouse.setProducts(new ArrayList<>());
        warehouse.setCompany(company);
        return warehouse;
    }

    @Override
    public Warehouse convertFromDTO(WarehousePutDTO dto, Long entityId) {
        if (dto == null) {
            return null;
        }
        Warehouse warehouse = warehouseRepository.findById(entityId).get();
        Address address = getAddressFromDTO(warehouse, dto);
        warehouse.setName(dto.getName());
        warehouse.setAddress(address);
        return warehouse;
    }


    private Address getAddressFromDTO(Warehouse warehouse, WarehousePutDTO dto) {
        if (warehouse.getAddress() == null) {
            return addressConverter.createEntityFromPut(dto.getAddress());
        }
        Long addressId = warehouse.getAddress().getId();
        return addressConverter.convertFromDTO(dto.getAddress(), addressId);
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Autowired
    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
}
