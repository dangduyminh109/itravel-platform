package com.itravel.platform.modules.identity.domain.customer.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidCustomerRoleException extends DomainException {
    public InvalidCustomerRoleException() {
        super(DomainErrorCode.INVALID_CUSTOMER_ROLE);
    }
}

