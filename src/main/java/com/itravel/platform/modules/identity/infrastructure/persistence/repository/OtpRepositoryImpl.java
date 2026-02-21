package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Otp;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.OtpCode;
import com.itravel.platform.modules.identity.domain.repository.OtpRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.OtpJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.OtpMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OtpRepositoryImpl implements OtpRepository {
    OtpJpaRepository repository;

    @Override
    public Optional<Otp> findByEmailAndCode(Email email, OtpCode otpCode) {
        return repository.findByEmailAndCode(email.value(),otpCode.value())
                .map(OtpMapper::toOtpDomain);
    }

    @Override
    public Optional<Otp> findFirstByEmailAndExpiresAtAfter(Email email, Instant currentTime) {
        return repository.findFirstByEmailAndExpiresAtAfter(email.value(),currentTime)
                .map(OtpMapper::toOtpDomain);
    }

    @Override
    public Otp save(Otp otp) {
        OtpJpaEntity otpToSave = OtpJpaEntity.builder()
                .email(otp.getEmail().value())
                .code(otp.getCode().value())
                .expiresAt(otp.getExpiresAt())
                .build();

        repository.save(otpToSave);
        return OtpMapper.toOtpDomain(otpToSave);
    }

    @Override
    public void destroy(Long id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }
}
