package dev.szafraniak.bmresource.model.entity.contact;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class IndividualContact extends Contact {

    @NotNull
    @Length(max = 20)
    @Pattern(regexp = Regexps.WORD_1_20)
    private String firstName;

    @NotNull
    @Length(max = 20)
    @Pattern(regexp = Regexps.WORD_1_20)
    private String lastName;

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

}
