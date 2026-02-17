package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class RoleNotExistException extends ApplicationException {
    public RoleNotExistException() {
        super(ApplicationErrorCode.ROLE_NOT_EXIST);
    }
}
