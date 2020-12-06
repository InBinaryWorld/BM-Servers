package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.dto.entity.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.model.entity.BaseCompanyEntity;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.User;
import dev.szafraniak.bmresource.repository.CompanyRepositoryInterface;
import dev.szafraniak.bmresource.repository.entity.*;
import dev.szafraniak.bmresource.services.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PermissionChecker {

    private UserService userService;
    private CompanyRepository companyRepo;
    private InvoiceRepository invoiceRepository;
    private ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;
    private BankAccountRepository bankAccountRepository;
    private FinancesRowRepository financesRowRepository;
    private ServiceModelRepository serviceModelRepository;
    private ProductModelRepository productModelRepository;
    private CompanyContactRepository companyContactRepository;
    private IndividualContactRepository individualContactRepository;


    public boolean checkCompanyId(Long companyId) {
        Optional<Company> company = companyRepo.findById(companyId);
        return checkCompany(company);
    }

    public boolean checkCompanyContact(Long companyId, Long contactId) {
        return checkEntityId(companyId, contactId, companyContactRepository);
    }

    public boolean checkIndividualContact(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, individualContactRepository);
    }

    public boolean checkProductModel(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, productModelRepository);
    }

    public boolean checkServiceModel(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, serviceModelRepository);
    }

    public boolean checkFinanceRow(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, financesRowRepository);
    }

    public boolean checkWarehouse(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, warehouseRepository);
    }

    public boolean checkInvoice(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, invoiceRepository);
    }

    public boolean checkProduct(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, productRepository);
    }

    public boolean checkBankAccountId(Long companyId, Long entityId) {
        return checkEntityId(companyId, entityId, bankAccountRepository);
    }

    public boolean checkProductForCreate(ProductPostDTO dto, Long companyId) {
        Optional<Company> productModel = extractCompany(productModelRepository, dto.getProductModelId());
        Optional<Company> warehouse = extractCompany(warehouseRepository, dto.getWarehouseId());

        Optional<Company> joined = innerJoin(productModel, warehouse);
        return checkCompany(joined, companyId);
    }

    private boolean checkCompany(Optional<Company> company, Long companyId) {
        Optional<Company> filtered = company.filter(cmp -> companyIdFilter(cmp, companyId));
        return checkCompany(filtered);
    }

    private boolean checkCompany(Optional<Company> company) {
        return company
                .map(Company::getOwner)
                .map(User::getId)
                .map(userService::isContextUser)
                .orElse(false);
    }

    private <T> Optional<T> innerJoin(Optional<T> t1, Optional<T> t2) {
        return t1.flatMap(t -> t2.filter(t::equals));
    }

    private <T extends BaseCompanyEntity> boolean checkEntityId(
            Long companyId, Long entityId, CompanyRepositoryInterface<T> repo) {
        Optional<Company> company = extractCompany(repo, entityId);
        return checkCompany(company, companyId);
    }

    private <T extends BaseCompanyEntity> Optional<Company> extractCompany(
            CompanyRepositoryInterface<T> repository, Long entityId) {
        return repository.findById(entityId)
                .map(T::getCompany);
    }

    private boolean companyIdFilter(Company company, Long companyId) {
        return companyId.equals(company.getId());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCompanyRepo(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Autowired
    public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Autowired
    public void setFinancesRowRepository(FinancesRowRepository financesRowRepository) {
        this.financesRowRepository = financesRowRepository;
    }

    @Autowired
    public void setServiceModelRepository(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }

    @Autowired
    public void setProductModelRepository(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }

    @Autowired
    public void setCompanyContactRepository(CompanyContactRepository companyContactRepository) {
        this.companyContactRepository = companyContactRepository;
    }

    @Autowired
    public void setIndividualContactRepository(IndividualContactRepository individualContactRepository) {
        this.individualContactRepository = individualContactRepository;
    }
}
