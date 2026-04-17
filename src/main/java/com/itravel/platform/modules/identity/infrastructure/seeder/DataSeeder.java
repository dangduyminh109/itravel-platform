package com.itravel.platform.modules.identity.infrastructure.seeder;

import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.domain.account.AccountLinkType;
import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.modules.identity.domain.account.AuthProvider;
import com.itravel.platform.modules.identity.domain.role.PermissionCode;
import com.itravel.platform.modules.identity.domain.account.RawPassword;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.*;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.*;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataSeeder implements ApplicationRunner {
    PermissionJpaRepository permissionJpaRepository;
    RoleJpaRepository roleJpaRepository;
    UserJpaRepository userJpaRepository;
    AccountJpaRepository accountJpaRepository;
    AccountLinkJpaRepository accountLinkJpaRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (permissionJpaRepository.count() == 0) {
            List<PermissionCode> permissionList = Arrays.asList(PermissionCode.values());
            Set<PermissionJpaEntity> permissionJpaEntityList = permissionList.stream()
                    .map(item -> PermissionJpaEntity.builder()
                            .code(item.getCode())
                            .build()
                    )
                    .collect(Collectors.toSet());
            permissionJpaRepository.saveAll(permissionJpaEntityList);
        }
        if(!roleJpaRepository.existsByName("admin")){
            List<PermissionJpaEntity> allPermission = permissionJpaRepository.findAll();
            RoleJpaEntity admin = RoleJpaEntity.builder()
                    .name("admin")
                    .permissionList(new HashSet<>(allPermission))
                    .status("ACTIVE")
                    .build();

            roleJpaRepository.save(admin);
        }
        if(!roleJpaRepository.existsByName("customer")){
            RoleJpaEntity customer = RoleJpaEntity.builder()
                    .name("customer")
                    .status("ACTIVE")
                    .build();
            roleJpaRepository.save(customer);
        }

        Optional<AccountJpaEntity> hasAdmin = accountJpaRepository.findByUsername("admin");
        if (hasAdmin.isEmpty()){
            RoleJpaEntity adminRole = roleJpaRepository.findByName("admin")
                    .orElseThrow(RoleNotExistException::new);

            AccountJpaEntity accountJpa = AccountJpaEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .password(passwordEncoderAdapter.encode(new RawPassword("Admin@123")).value())
                    .authProvider(AuthProvider.USERNAME)
                    .status(AccountStatus.ACTIVE)
                    .createdAt(Instant.now())
                    .username("admin")
                    .roleList(Set.of(adminRole))
                    .build();

            UserJpaEntity admin = UserJpaEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .fullName("admin")
                    .createdAt(Instant.now())
                    .build();

            AccountLinkJpaEntity accountLinkJpaEntity = AccountLinkJpaEntity.builder()
                    .accountId(accountJpa.getId())
                    .targetId(admin.getId())
                    .targetType(AccountLinkType.SYSTEM_USER.toString())
                    .build();

            accountLinkJpaRepository.save(accountLinkJpaEntity);
            accountJpaRepository.save(accountJpa);
            userJpaRepository.save(admin);
        }
    }
}
