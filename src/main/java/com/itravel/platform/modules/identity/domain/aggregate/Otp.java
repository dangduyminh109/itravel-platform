package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import com.itravel.platform.modules.identity.domain.exception.InvalidOtpCodeException;
import com.itravel.platform.modules.identity.domain.exception.OtpExpiredException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Otp {
    final Long id;
    final Email email;
    OtpCode code;
    final Instant expiresAt;

    private Otp(Email email, OtpCode code, Duration otpDuration) {
        this.id = null;
        this.email = email;
        this.code = code;
        this.expiresAt = Instant.now().plus(otpDuration);
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Otp fromExisting (
            Long id,
            Email email,
            OtpCode code,
            Instant expiresAt
    ) {
        return new Otp(id, email, code, expiresAt);
    }

    public static Otp create(
            Email email,
            OtpCode code,
            Duration otpDuration
    ){
        return new Otp(email, code, otpDuration);
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void verify(OtpCode inputCode) {
        if (isExpired()) {
            throw new OtpExpiredException();
        }

        if (!this.code.equals(inputCode)) {
            throw new InvalidOtpCodeException();
        }
    }
}
