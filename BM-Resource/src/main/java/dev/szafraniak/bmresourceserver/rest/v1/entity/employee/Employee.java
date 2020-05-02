package dev.szafraniak.bmresourceserver.rest.v1.entity.employee;

import dev.szafraniak.bmresourceserver.rest.v1.entity.person.Person;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Employee extends Person {
    private String status;
    private String job;
    private Date lastModification;
    private BigDecimal salary;
    private List<WorkHistoryEntity> workHistory;
}