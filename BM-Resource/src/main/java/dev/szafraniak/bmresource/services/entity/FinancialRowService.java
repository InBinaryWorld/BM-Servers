package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.FinancialRowConverter;
import dev.szafraniak.bmresource.dto.finantialRow.FinancialRowGetDTO;
import dev.szafraniak.bmresource.dto.finantialRow.FinancialRowPostDTO;
import dev.szafraniak.bmresource.dto.finantialRow.FinancialRowPutDTO;
import dev.szafraniak.bmresource.entity.FinancialRow;
import dev.szafraniak.bmresource.repository.entity.FinancesRowRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialRowService extends AbstractCompanyService<FinancialRow, FinancesRowRepository,
        FinancialRowConverter, FinancialRowGetDTO, FinancialRowPostDTO, FinancialRowPutDTO> {

    @Autowired
    public FinancialRowService(FinancialRowConverter converter, FinancesRowRepository repository) {
        super(converter, repository);
    }
}
