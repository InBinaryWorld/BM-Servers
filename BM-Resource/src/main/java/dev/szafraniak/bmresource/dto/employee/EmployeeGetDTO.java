package dev.szafraniak.bmresource.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeGetDTO implements GetDTOInterface {

    private Long id;

    private IndividualContactGetDTO individualContact;

    private String jobPosition;

    private LocalDate firstEmploymentDate;

    private BigDecimal salary;

//    private Company company;

}
