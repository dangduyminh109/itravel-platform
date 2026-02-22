package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Otp;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.OtpCode;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.OtpJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OtpMapper {
    default OtpJpaEntity toOtpJpaEntity(Otp otp) {
        return OtpJpaEntity.builder()
                .id(otp.getId())
                .email(otp.getEmail().value())
                .code(otp.getCode().value())
                .expiresAt(otp.getExpiresAt())
                .build();
    }

    static Otp toOtpDomain(OtpJpaEntity entity) {
        return Otp.fromExistingBuilder()
                .id(entity.getId())
                .email(new Email(entity.getEmail()))
                .code(new OtpCode(entity.getCode()))
                .expiresAt(entity.getExpiresAt())
                .build();
    }
}
