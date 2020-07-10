package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.address.AddressPostDTO;
import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    private ModelMapper modelMapper;
    private AddressRepository addressRepository;

    public AddressGetDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressGetDTO.class);
    }

    public Address convertFromDTO(AddressPostDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    public Address convertFromDTO(AddressPutDTO dto, Long addressId) {
        Address address = addressRepository.findById(addressId).get();
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setPostalCode(dto.getPostalCode());
        address.setAddressLine1(dto.getAddressLine1());
        address.setAddressLine2(dto.getAddressLine2());
        return address;
    }


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

}
