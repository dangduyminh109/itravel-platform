package com.itravel.platform.modules.identity.api.dto.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PastOrPresentDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PastOrPresentDate {
    String message() default "DATE_OF_BIRTH_MUST_BE_PAST_OR_PRESENT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

