package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.AddressConverter;
import dev.szafraniak.bmresource.dto.entity.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.entity.address.AddressPostDTO;
import dev.szafraniak.bmresource.dto.entity.address.AddressPutDTO;
import dev.szafraniak.bmresource.model.entity.Address;
import dev.szafraniak.bmresource.repository.entity.AddressRepository;
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
