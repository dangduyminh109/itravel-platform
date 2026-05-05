package com.itravel.platform.modules.notification.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidNotificationStateException extends DomainException {
    public InvalidNotificationStateException() {
        super(DomainErrorCode.INVALID_NOTIFICATION_STATE);
    }
}
