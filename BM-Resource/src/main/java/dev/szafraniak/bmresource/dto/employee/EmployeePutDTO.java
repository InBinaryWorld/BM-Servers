package dev.szafraniak.bmresource.dto.employee;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeePutDTO implements PutDTOInterface {

//    private Long id;

//    private Long individualContactId;

    @NotNull
    @Length(min = 2, max = 30)
    @Pattern(regexp = Regexps.WORDS)
    private String jobPosition;

    private LocalDate firstEmploymentDate;

    @Min(0)
    private BigDecimal salary;

//    private Company company;

}
