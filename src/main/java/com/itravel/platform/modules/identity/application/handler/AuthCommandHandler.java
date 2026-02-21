package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.query.AuthToken;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountLinkType;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.RefreshTokenRepository;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenGenerator;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import com.itravel.platform.modules.identity.domain.service.TokenProvider;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthCommandHandler {
    AccountRepository accountRepository;
    RefreshTokenRepository refreshTokenRepository;
    TokenProvider tokenProvider;
    RefreshTokenGenerator refreshTokenGenerator;
    RefreshTokenHasher refreshTokenHasher;
    PasswordEncoderAdapter passwordEncoderAdapter;
    @NonFinal
    @Value("${security.refresh-token.refreshable-duration}")
    long REFRESH_DURATION;

    public AuthToken login(LoginCommand command) throws JOSEException {
        Account account = accountRepository
                .findByIdentifier(command.identifier())
                .orElseThrow(AccountNotExistException::new);

        // check valid account
        if (AccountStatus.INACTIVE.equals(account.getStatus())) {
            throw new AccountInActiveException();
        }

        if (!Objects.isNull(account.getDeletedAt())) {
            throw new AccountDeletedException();
        }

        if(!passwordEncoderAdapter.matches(command.password().value(),account.getPassword())){
            if(account.getAuthProvider().equals(AuthProvider.USERNAME)){
                throw new UsernameOrPasswordInvalidException();
            } else{
                throw new EmailOrPasswordInvalidException();
            }
        }

        // create token
        Set<Permission> permissionSet = account.getRoleList().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .collect(Collectors.toSet());
        String permissionList = permissionSet.stream()
                .map(Permission::code)
                .collect(Collectors.joining(","));
        String roleList = account.getRoleList().stream()
                .map(role -> role.getName().value())
                .collect(Collectors.joining(","));

        AccountLinkType type = AuthProvider.USERNAME.equals(account.getAuthProvider())
                ? AccountLinkType.SYSTEM_USER : AccountLinkType.CUSTOMER;

        String accessToken = tokenProvider.generateAccessToken(
                account.getId(),
                type,
                roleList,permissionList
        );

        String refreshTokenRaw = refreshTokenGenerator.generate();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);
        Duration duration = Duration.ofSeconds(REFRESH_DURATION);
        RefreshToken refreshToken = RefreshToken.create(
                account.getId(),
                refreshTokenHash,
                duration
        );
        refreshTokenRepository.save(refreshToken);
        return new AuthToken(
                accessToken,
                refreshTokenRaw,
                refreshToken.getExpiresAt()
        );
    }
}