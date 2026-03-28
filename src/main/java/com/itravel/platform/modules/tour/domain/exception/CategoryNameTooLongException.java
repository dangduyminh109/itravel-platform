package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class CategoryNameTooLongException extends DomainException {
    public CategoryNameTooLongException() {
        super(DomainErrorCode.CATEGORY_NAME_TOO_LONG);
    }
}

