package com.itravel.platform.modules.identity.domain.aggregate.valueobject;
import com.itravel.platform.modules.identity.domain.exception.InvalidOtpCodeException;

import java.util.regex.Pattern;

public record OtpCode(String value) {
    private static final Pattern OTP_PATTERN = Pattern.compile("^\\d{6}$");

    public OtpCode {
        if (value == null || !OTP_PATTERN.matcher(value).matches()) {
            throw new InvalidOtpCodeException();
        }
    }
}