package com.itravel.platform.modules.identity.api.dto.request.validation;

import com.itravel.platform.modules.identity.api.dto.request.PassportRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IssueDateBeforeExpiryDateValidator implements ConstraintValidator<IssueDateBeforeExpiryDate, PassportRequest> {

    @Override
    public boolean isValid(PassportRequest value, ConstraintValidatorContext context) {
        if (value == null || value.issueDate() == null || value.expiryDate() == null) {
            return true;
        }
        return value.issueDate().isBefore(value.expiryDate());
    }
}

