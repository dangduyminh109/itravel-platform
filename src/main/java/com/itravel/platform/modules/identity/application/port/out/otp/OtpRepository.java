package com.itravel.platform.modules.identity.application.port.out.otp;

import com.itravel.platform.modules.identity.domain.otp.Otp;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.otp.OtpCode;
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

