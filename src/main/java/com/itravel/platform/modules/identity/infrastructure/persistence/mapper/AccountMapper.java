package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {
    @Mapping(target = "id", expression = "java(account.getId() != null ? account.getId().value() : null)")
    @Mapping(target = "username", expression = "java(account.getUsername() != null ? account.getUsername().value() : null)")
    @Mapping(target = "password", expression = "java(account.getPassword() != null ? account.getPassword().value() : null)")
    @Mapping(target = "email", expression = "java(account.getEmail() != null ? account.getEmail().value() : null)")
    AccountJpaEntity toAccountJpaEntity(Account account);

    static Account toAccountDomain(AccountJpaEntity entity) {
        return Account.fromExistingBuilder()
                .id(new AccountId(entity.getId()))
                .password(entity.getPassword() !=null ? new PasswordHash(entity.getPassword()) : null)
                .username(entity.getUsername() !=null ? new Username(entity.getUsername()) : null)
                .email(entity.getEmail() !=null ? new Email(entity.getEmail()) : null)
                .authProvider(AuthProvider.valueOf(entity.getAuthProvider()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
