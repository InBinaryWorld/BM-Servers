package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.AddressConverter;
import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.address.AddressPostDTO;
import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.repository.entity.AddressRepository;
import dev.szafraniak.bmresource.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends AbstractService<Address, AddressRepository,
        AddressConverter, AddressGetDTO, AddressPostDTO, AddressPutDTO> {

    @Autowired
    public AddressService(AddressConverter converter, AddressRepository repository) {
        super(converter, repository);
    }
}
