package dev.szafraniak.bmresource.dto.entity.finantialRow;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.config.BaseEnvironment;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinancialRowGetDTO implements GetDTOInterface {

    private Long id;

    private String title;

    private OffsetDateTime eventDate;

    private String description;

    private BigDecimal amountChange;

    private String currency = BaseEnvironment.CURRENCY;

//    private Company company;

}
