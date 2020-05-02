package dev.szafraniak.bmresourceserver.rest.v1.entity.user;

import dev.szafraniak.bmresourceserver.rest.v1.entity.Address;
import dev.szafraniak.bmresourceserver.rest.v1.entity.employee.Employee;
import dev.szafraniak.bmresourceserver.rest.v1.entity.invoice.Invoice;
import dev.szafraniak.bmresourceserver.rest.v1.entity.offer.Product;
import dev.szafraniak.bmresourceserver.rest.v1.entity.offer.Services;
import dev.szafraniak.bmresourceserver.rest.v1.entity.warehouse.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class Company {
    private String id;
    private String name;
    private String nip;
    private String invoicePrefix;
    private String currency;
    private User owner;
    private Address address;
    private List<Warehouse> warehouses;
    private List<Product> product;
    private List<Services> services;
    private List<Invoice> invoices;
    private List<BankAccount> bankAccounts;
    private List<Employee> employees;
}
