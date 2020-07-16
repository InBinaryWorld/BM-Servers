package dev.szafraniak.bmresource.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnvironmentIdValidator.class)
public @interface VerifyEnvironmentId {
    String message() default "Quantity unit id does not match server environment";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    EnvironmentIds source();

}
