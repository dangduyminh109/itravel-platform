package com.itravel.platform.modules.identity.api.dto.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IssueDateBeforeExpiryDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IssueDateBeforeExpiryDate {
    String message() default "ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

