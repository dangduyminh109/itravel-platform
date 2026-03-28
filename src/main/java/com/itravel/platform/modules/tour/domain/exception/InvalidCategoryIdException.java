package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidCategoryIdException extends DomainException {
    public InvalidCategoryIdException() {
        super(DomainErrorCode.INVALID_CATEGORY_ID);
    }
}

