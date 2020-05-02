package dev.szafraniak.bmresourceserver.rest.v1.entity.offer;

import dev.szafraniak.bmresourceserver.rest.v1.entity.user.Company;

import java.util.List;

public class ProductGroup {
    private String id;
    private String name;
    private Company company;
    private List<Product> products;
}
