package dev.szafraniak.bmresource.validator;

import dev.szafraniak.bmresource.config.BaseEnvironment;

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
            case QUANTITY_UNIT_PRODUCT:
                validationList = BaseEnvironment.QUANTITY_UNITS_PRODUCT_IDS;
                break;
            case QUANTITY_UNIT_SERVICE:
                validationList = BaseEnvironment.QUANTITY_UNITS_SERVICE_IDS;
                break;
            default:
                validationList = new ArrayList<>();
        }
    }

    @Override
    public boolean isValid(String unitId, ConstraintValidatorContext context) {
        return unitId == null || validationList.contains(unitId);
    }
}