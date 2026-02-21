package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Otp;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.OtpCode;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Optional;

@Repository
public interface OtpRepository {
    Optional<Otp> findByEmailAndCode(Email email, OtpCode otpCode);
    Optional<Otp> findFirstByEmailAndExpiresAtAfter(Email email, Instant currentTime);
    Otp save(Otp otp);
    void destroy(Long id);
}
