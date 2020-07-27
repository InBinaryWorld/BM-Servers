package dev.szafraniak.bmresource.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentIdValidator implements ConstraintValidator<VerifyEnvironmentId, String> {

    List<String> validationList;

    public EnvironmentIdValidator() {
        super();
    }

    @Override
    public void initialize(VerifyEnvironmentId constraintAnnotation) {
        switch (constraintAnnotation.source()) {
            default:
                validationList = new ArrayList<>();
        }
    }

    @Override
    public boolean isValid(String unitId, ConstraintValidatorContext context) {
        return unitId == null || validationList.contains(unitId);
    }
}