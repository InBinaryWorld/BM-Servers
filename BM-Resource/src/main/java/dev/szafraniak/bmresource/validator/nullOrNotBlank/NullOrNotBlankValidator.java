package dev.szafraniak.bmresource.validator.nullOrNotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    public void initialize(NullOrNotBlank constraint) {
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return obj == null || !obj.isBlank();
    }
}
