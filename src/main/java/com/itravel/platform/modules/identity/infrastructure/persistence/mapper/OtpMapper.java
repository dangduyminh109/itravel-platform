package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Otp;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.OtpCode;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.OtpJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OtpMapper {
    static Otp toOtpDomain(OtpJpaEntity entity) {
        return Otp.fromExistingBuilder()
                .id(entity.getId())
                .email(new Email(entity.getEmail()))
                .code(new OtpCode(entity.getCode()))
                .expiresAt(entity.getExpiresAt())
                .build();
    }
}
