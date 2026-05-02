package com.itravel.platform.modules.booking.application.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InventoryUnavailableException extends DomainException {
    public InventoryUnavailableException() {
        super(DomainErrorCode.INVENTORY_UNAVAILABLE);
    }
}
