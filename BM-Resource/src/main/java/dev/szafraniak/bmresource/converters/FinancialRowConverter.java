package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.finantialRow.FinancialRowGetDTO;
import dev.szafraniak.bmresource.dto.finantialRow.FinancialRowPostDTO;
import dev.szafraniak.bmresource.dto.finantialRow.FinancialRowPutDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.FinancialRow;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.FinancesRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialRowConverter implements ConverterCompanyInterface<FinancialRow, FinancialRowGetDTO, FinancialRowPostDTO, FinancialRowPutDTO> {

    private CompanyRepository companyRepository;
    private FinancesRowRepository financesRowRepository;

    public FinancialRowGetDTO convertToDTO(FinancialRow financialRow) {
        if (financialRow == null) {
            return null;
        }
        FinancialRowGetDTO dto = new FinancialRowGetDTO();
        dto.setId(financialRow.getId());
        dto.setTitle(financialRow.getTitle());
        dto.setEventDate(financialRow.getEventDate());
        dto.setAmountChange(financialRow.getAmountChange());
        dto.setDescription(financialRow.getDescription());
        return dto;
    }

    public FinancialRow convertFromDTO(FinancialRowPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        FinancialRow financialRow = new FinancialRow();
        financialRow.setTitle(dto.getTitle());
        financialRow.setEventDate(dto.getEventDate());
        financialRow.setAmountChange(dto.getAmountChange());
        financialRow.setDescription(dto.getDescription());
        financialRow.setCompany(company);
        return financialRow;
    }

    public FinancialRow convertFromDTO(FinancialRowPutDTO dto, Long financialRowId) {
        if (dto == null) {
            return null;
        }
        FinancialRow financialRow = financesRowRepository.findById(financialRowId).get();
        financialRow.setTitle(dto.getTitle());
        financialRow.setEventDate(dto.getEventDate());
        financialRow.setAmountChange(dto.getAmountChange());
        financialRow.setDescription(dto.getDescription());
        return financialRow;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setFinancesRowRepository(FinancesRowRepository financesRowRepository) {
        this.financesRowRepository = financesRowRepository;
    }
}
