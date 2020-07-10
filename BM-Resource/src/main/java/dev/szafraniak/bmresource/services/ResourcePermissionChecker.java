package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourcePermissionChecker {

    private UserService userService;
    private CompanyRepository companyRepo;

    public boolean checkCompanyId(Long companyId) {
        Long userId = userService.getContextUserId();
        return companyRepo.findById(companyId)
                .map(company -> company.getOwner().getId())
                .map(userId::equals).orElse(false);
    }

    @Autowired
    public void setCompanyRepo(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
