package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.dto.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.dto.shared.BasePostDTO;
import dev.szafraniak.bmresource.entity.*;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.repository.ProductGroupRepository;
import dev.szafraniak.bmresource.repository.ProductModelRepository;
import dev.szafraniak.bmresource.repository.ServiceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PermissionChecker {

    private UserService userService;
    private CompanyRepository companyRepo;
    private ProductGroupRepository groupRepository;
    private ServiceModelRepository serviceModelRepository;
    private ProductModelRepository productModelRepository;
    private ProductGroupRepository productGroupRepository;


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


    public boolean checkForCreate(ProductModelPostDTO dto, Long companyId) {
        if (dto.getProductGroup() == null) {
            return checkCompanyId(companyId);
        }
        BasePostDTO group = dto.getProductGroup();
        Optional<Company> opt = groupRepository
                .findById(group.getId())
                .map(ProductGroup::getCompany);
        return checkCompany(opt, companyId);
    }


    public boolean checkForUpdate(ProductModelPutDTO dto,
                                  Long companyId,
                                  Long productModelId) {
        if (dto.getProductGroup() == null) {
            return checkProductModel(companyId, productModelId);
        }
        Long groupId = dto.getProductGroup().getId();
        return checkProductModel(companyId, productModelId, groupId);
    }

    public boolean checkProductModel(Long companyId, Long productModelId) {
        Optional<Company> company = productModelRepository
                .findById(productModelId)
                .map(ProductModel::getCompany);
        return checkCompany(company, companyId);
    }

    public boolean checkServiceModel(Long companyId, Long serviceModelId) {
        Optional<Company> company = serviceModelRepository
                .findById(serviceModelId)
                .map(ServiceModel::getCompany);
        return checkCompany(company, companyId);
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
}
