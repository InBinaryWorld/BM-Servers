package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class IndividualContact extends Contact {

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String firstName;

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String lastName;

    @Valid
    @OneToOne(mappedBy = "individualContact")
    private Employee employee;

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

}
