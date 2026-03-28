package com.itravel.platform.modules.tour.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class CategoryNameExistedException extends ApplicationException {
    public CategoryNameExistedException() {
        super(ApplicationErrorCode.CATEGORY_NAME_EXISTED);
    }
}

