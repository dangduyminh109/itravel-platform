package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    UserJpaEntity toUserJpaEntity(User user);

    static User toUserDomain(UserJpaEntity entity) {
        return User.fromExistingBuilder()
                .id(new UserId(entity.getId()))
                .fullName(new FullName(entity.getFullName()))
                .phoneNumber(entity.getPhoneNumber() != null ? new PhoneNumber(entity.getPhoneNumber()) : null)
                .avatar(entity.getAvatar() != null ? new Avatar(entity.getAvatar()) : null)
                .gender(entity.getGender())
                .email(entity.getEmail() != null ? new Email(entity.getEmail()) : null)
                .dateOfBirth(entity.getDateOfBirth())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
