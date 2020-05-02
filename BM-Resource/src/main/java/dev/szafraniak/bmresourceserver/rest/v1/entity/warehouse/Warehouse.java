package dev.szafraniak.bmresourceserver.rest.v1.entity.warehouse;

import dev.szafraniak.bmresourceserver.rest.v1.entity.Address;
import dev.szafraniak.bmresourceserver.rest.v1.entity.user.Company;
import lombok.Data;

import java.util.List;

@Data
public class Warehouse {
    private String id;
    private String name;
    private Address address;
    private Company company;
    private List<WarehouseProduct> products;
}
