package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterInterface;
import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.address.AddressPostDTO;
import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.repository.entity.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements ConverterInterface<Address, AddressGetDTO, AddressPostDTO, AddressPutDTO> {

    private AddressRepository addressRepository;

    public AddressGetDTO convertToDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressGetDTO dto = new AddressGetDTO();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setPostalCode(address.getPostalCode());
        dto.setStreet(address.getStreet());
        dto.setHouseNumber(address.getHouseNumber());
        dto.setApartmentNumber(address.getApartmentNumber());
        return dto;
    }

    public Address convertFromDTO(AddressPostDTO dto) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setPostalCode(dto.getPostalCode());
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        address.setApartmentNumber(dto.getApartmentNumber());
        return address;
    }

    public Address convertFromDTO(AddressPutDTO dto, Long addressId) {
        if (dto == null) {
            return null;
        }
        Address address = addressRepository.findById(addressId).get();
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setPostalCode(dto.getPostalCode());
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        address.setApartmentNumber(dto.getApartmentNumber());
        return address;
    }

    public Address createEntityFromPut(AddressPutDTO dto) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setPostalCode(dto.getPostalCode());
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());
        address.setApartmentNumber(dto.getApartmentNumber());
        return address;
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

}
