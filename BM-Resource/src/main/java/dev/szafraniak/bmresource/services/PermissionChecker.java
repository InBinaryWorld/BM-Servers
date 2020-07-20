package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.dto.employee.EmployeePostDTO;
import dev.szafraniak.bmresource.dto.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.entity.*;
import dev.szafraniak.bmresource.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PermissionChecker {

    private UserService userService;
    private CompanyRepository companyRepo;
    private InvoiceRepository invoiceRepository;
    private ProductRepository productRepository;
    private EmployeeRepository employeeRepository;
    private ProductGroupRepository groupRepository;
    private WarehouseRepository warehouseRepository;
    private FinancesRowRepository financesRowRepository;
    private ServiceModelRepository serviceModelRepository;
    private ProductModelRepository productModelRepository;
    private ProductGroupRepository productGroupRepository;
    private CompanyContactRepository companyContactRepository;
    private IndividualContactRepository individualContactRepository;


    public boolean checkCompanyId(Long companyId) {
        Optional<Company> company = companyRepo.findById(companyId);
        return checkCompany(company);
    }

    public boolean checkProductGroup(Long companyId, Long productGroupId) {
        Optional<Company> company = productGroupRepository
                .findById(productGroupId)
                .map(ProductGroup::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkCompanyContact(Long companyId, Long contactId) {
        Optional<Company> company = companyContactRepository
                .findById(contactId)
                .map(CompanyContact::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkIndividualContact(Long companyId, Long contactId) {
        Optional<Company> company = individualContactRepository
                .findById(contactId)
                .map(IndividualContact::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkForCreate(ProductModelPostDTO dto, Long companyId) {
        if (dto.getProductGroupId() == null) {
            return checkCompanyId(companyId);
        }
        Optional<Company> opt = groupRepository
                .findById(dto.getProductGroupId())
                .map(ProductGroup::getCompany);
        return checkCompany(opt, companyId);
    }

    public boolean checkForCreate(EmployeePostDTO dto, Long companyId) {
        Optional<Company> opt = individualContactRepository
                .findById(dto.getIndividualId())
                .map(IndividualContact::getCompany);
        return checkCompany(opt, companyId);
    }


    public boolean checkForUpdate(ProductModelPutDTO dto,
                                  Long companyId,
                                  Long productModelId) {
        if (dto.getProductGroupId() == null) {
            return checkProductModel(companyId, productModelId);
        }
        Long groupId = dto.getProductGroupId();
        return checkProductModel(companyId, productModelId, groupId);
    }

    public boolean checkProductModel(Long companyId, Long productModelId) {
        Optional<Company> company = productModelRepository
                .findById(productModelId)
                .map(ProductModel::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkEmployee(Long companyId, Long entityId) {
        Optional<Company> company = employeeRepository
                .findById(entityId)
                .map(Employee::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkServiceModel(Long companyId, Long serviceModelId) {
        Optional<Company> company = serviceModelRepository
                .findById(serviceModelId)
                .map(ServiceModel::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkFinanceRow(Long companyId, Long financesRowId) {
        Optional<Company> company = financesRowRepository
                .findById(financesRowId)
                .map(FinancialRow::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkWarehouse(Long companyId, Long entityId) {
        Optional<Company> company = warehouseRepository
                .findById(entityId)
                .map(Warehouse::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkInvoice(Long companyId, Long invoiceId) {
        Optional<Company> company = invoiceRepository
                .findById(invoiceId)
                .map(Invoice::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkProduct(Long companyId, Long productId) {
        Optional<Company> company = productRepository
                .findById(productId)
                .map(Product::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkProductForCreate(ProductPostDTO dto, Long companyId) {
        Optional<Company> productModel = productModelRepository
                .findById(dto.getProductModelId())
                .map(ProductModel::getCompany);

        Optional<Company> warehouse = warehouseRepository
                .findById(dto.getWarehouseId())
                .map(Warehouse::getCompany);

        Optional<Company> joined = innerJoin(productModel, warehouse);
        return checkCompany(joined, companyId);
    }

    private boolean checkProductModel(Long companyId, Long productModelId, Long groupId) {
        Optional<Company> productCompany = productModelRepository
                .findById(productModelId)
                .map(ProductModel::getCompany);

        Optional<Company> groupCompany = groupRepository
                .findById(groupId)
                .map(ProductGroup::getCompany);

        Optional<Company> joined = innerJoin(productCompany, groupCompany);
        return checkCompany(joined, companyId);
    }

    private boolean checkCompany(Optional<Company> company, Long companyId) {
        Optional<Company> filtered = company.filter(cmp -> companyIdFilter(cmp, companyId));
        return checkCompany(filtered);
    }

    private boolean checkCompany(Optional<Company> company) {
        Long userId = userService.getContextUserId();
        return company
                .map(Company::getOwner)
                .map(User::getId)
                .map(userId::equals)
                .orElse(false);
    }

    private <T> Optional<T> innerJoin(Optional<T> t1, Optional<T> t2) {
        return t1.flatMap(t -> t2.filter(t::equals));
    }

    private boolean companyIdFilter(Company company, Long companyId) {
        return companyId.equals(company.getId());
    }

    @Autowired
    public void setCompanyRepo(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGroupRepository(ProductGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setProductModelRepository(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }

    @Autowired
    public void setProductGroupRepository(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    @Autowired
    public void setServiceModelRepository(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setCompanyContactRepository(CompanyContactRepository companyContactRepository) {
        this.companyContactRepository = companyContactRepository;
    }

    @Autowired
    public void setIndividualContactRepository(IndividualContactRepository individualContactRepository) {
        this.individualContactRepository = individualContactRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setFinancesRowRepository(FinancesRowRepository financesRowRepository) {
        this.financesRowRepository = financesRowRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
}
