package com.itravel.platform.modules.identity.api.dto.request.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.isAfter(LocalDateTime.now());
    }
}

