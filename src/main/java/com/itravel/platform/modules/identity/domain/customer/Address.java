package com.itravel.platform.modules.identity.domain.customer;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.modules.identity.domain.customer.exception.InvalidAddressException;

public record Address(
        String detail,
        Long wardId,
        Long provinceId
) {
    public Address {
        if (detail == null || detail.isBlank()) {
            throw new InvalidAddressException(DomainErrorCode.ADDRESS_DETAIL_CANNOT_BE_BLANK);
        }
        if (wardId == null) {
            throw new InvalidAddressException(DomainErrorCode.WARD_ID_CANNOT_BE_NULL);
        }
        if (provinceId == null) {
            throw new InvalidAddressException(DomainErrorCode.PROVINCE_ID_CANNOT_BE_NULL);
        }
    }
}



