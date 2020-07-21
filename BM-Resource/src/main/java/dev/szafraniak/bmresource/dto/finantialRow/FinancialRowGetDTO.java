package dev.szafraniak.bmresource.dto.finantialRow;

import com.fasterxml.jackson.annotation.JsonInclude;
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

//    private Company company;

}
