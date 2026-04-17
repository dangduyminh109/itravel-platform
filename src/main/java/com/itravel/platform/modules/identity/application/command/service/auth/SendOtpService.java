package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.SendOtpCommand;
import com.itravel.platform.modules.identity.application.port.in.auth.SendOtpUseCase;
import com.itravel.platform.modules.identity.application.port.out.otp.OtpRepository;
import com.itravel.platform.modules.identity.application.port.out.email.EmailSenderPort;
import com.itravel.platform.modules.identity.domain.otp.Otp;
import com.itravel.platform.modules.identity.domain.otp.OtpCode;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SendOtpService implements SendOtpUseCase {
    OtpRepository otpRepository;
    EmailSenderPort emailSenderPort;

    @NonFinal
    @Value("${security.otp.otp-duration}")
    long VALID_DURATION_OTP;

    @Override
    @Transactional
    public String execute(SendOtpCommand command) throws MessagingException {
        Optional<Otp> existingOtp = otpRepository.findFirstByEmailAndExpiresAtAfter(
                command.email(), Instant.now());

        if (existingOtp.isPresent()) {
            return "The email has been sent.";
        }

        Otp otp = Otp.create(
                command.email(),
                new OtpCode(generateOtpCode()),
                Duration.ofMinutes(VALID_DURATION_OTP)
        );

        Otp result = otpRepository.save(otp);
        emailSenderPort.sendEmail(command.email().value(), result.getCode().value());
        return "Email đã được gửi";
    }

    private String generateOtpCode() {
        Random random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
