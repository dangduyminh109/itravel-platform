package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.otp.Otp;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.otp.OtpCode;
import com.itravel.platform.modules.identity.application.port.out.otp.OtpRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.OtpJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.OtpMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.OtpJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpRepositoryImpl implements OtpRepository {
    OtpJpaRepository repository;
    OtpMapper otpMapper;

    @Override
    public Optional<Otp> findByEmailAndCode(Email email, OtpCode otpCode) {
        return repository.findByEmailAndCode(email.value(), otpCode.value())
                .map(OtpMapper::toOtpDomain);
    }

    @Override
    public Optional<Otp> findFirstByEmailAndExpiresAtAfter(Email email, Instant currentTime) {
        return repository.findFirstByEmailAndExpiresAtAfter(email.value(), currentTime)
                .map(OtpMapper::toOtpDomain);
    }

    @Override
    public Otp save(Otp otp) {
        OtpJpaEntity entity = otpMapper.toOtpJpaEntity(otp);
        OtpJpaEntity savedEntity = repository.save(entity);
        return OtpMapper.toOtpDomain(savedEntity);
    }

    @Override
    public void destroy(Long id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }
}
