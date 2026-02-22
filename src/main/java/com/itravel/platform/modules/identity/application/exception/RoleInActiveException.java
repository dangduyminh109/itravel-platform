package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class RoleInActiveException extends ApplicationException {
    public RoleInActiveException() {
        super(ApplicationErrorCode.ROLE_INACTIVE);
    }
}
