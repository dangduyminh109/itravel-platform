package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidCategoryNameException extends DomainException {
    public InvalidCategoryNameException() {
        super(DomainErrorCode.INVALID_CATEGORY_NAME);
    }
}

