package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class RoleExistedException extends ApplicationException {
    public RoleExistedException() {
        super(ApplicationErrorCode.ROLE_EXISTED);
    }
}
