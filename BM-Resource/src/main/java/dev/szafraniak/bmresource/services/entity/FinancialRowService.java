package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.FinancialRowConverter;
import dev.szafraniak.bmresource.dto.entity.finantialRow.FinancialRowGetDTO;
import dev.szafraniak.bmresource.dto.entity.finantialRow.FinancialRowPostDTO;
import dev.szafraniak.bmresource.dto.entity.finantialRow.FinancialRowPutDTO;
import dev.szafraniak.bmresource.model.entity.FinancialRow;
import dev.szafraniak.bmresource.repository.entity.FinancesRowRepository;
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
