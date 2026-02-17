package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class AdminRoleCanNotDeleteException extends ApplicationException {
    public AdminRoleCanNotDeleteException() {
        super(ApplicationErrorCode.ADMIN_ROLE_CAN_NOT_DELETE);
    }
}
