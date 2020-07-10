package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.AddressConverter;
import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressConverter addressConverter;
    private AddressRepository addressRepository;

    public AddressGetDTO updateAddress(AddressPutDTO dto, Long addressId) {
        Address address = addressConverter.convertFromDTO(dto, addressId);
        Address saved = addressRepository.save(address);
        return addressConverter.convertToDTO(saved);
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Autowired
    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }
}
