package com.itravel.platform.modules.tour.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class CategoryNotFoundException extends ApplicationException {
    public CategoryNotFoundException() {
        super(ApplicationErrorCode.CATEGORY_NOT_EXIST);
    }
}

