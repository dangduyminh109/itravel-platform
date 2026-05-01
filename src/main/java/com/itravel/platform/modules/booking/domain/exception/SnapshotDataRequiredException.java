package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class SnapshotDataRequiredException extends DomainException {
    public SnapshotDataRequiredException() {
        super(DomainErrorCode.SNAPSHOT_DATA_REQUIRED);
    }
}
