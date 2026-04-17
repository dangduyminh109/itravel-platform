package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.account.Username;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.PermissionOverrideJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.AccountMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRepositoryImpl implements AccountRepository {
    AccountJpaRepository repository;
    AccountMapper mapper;

    @Override
    public Optional<Account> findById(AccountId id) {
        return repository.findById(id.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByUsername(Username username) {
        return repository.findByUsername(username.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByEmail(Email email) {
        return repository.findByEmail(email.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier)
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByIdWithRolesAndPermissions(AccountId id) {
        return repository.findByIdWithRolesAndPermissions(id.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public void save(Account account) {
        AccountJpaEntity accountJpa = mapper.toAccountJpaEntity(account);
        Set<PermissionOverrideJpaEntity> permissionOverrides = account.getPermissionOverrides().stream()
                .map(permissionOverride -> PermissionOverrideJpaEntity.builder()
                        .accountId(account.getId().value())
                        .permission(permissionOverride.getPermission().code())
                        .permissionType(permissionOverride.getPermissionType())
                        .account(accountJpa)
                        .build())
                .collect(Collectors.toSet());
        accountJpa.setPermissionOverrides(permissionOverrides);
        repository.save(accountJpa);
    }

    @Override
    public void destroy(AccountId accountId) {
        repository.findById(accountId.value())
                .ifPresent(repository::delete);
    }
}
