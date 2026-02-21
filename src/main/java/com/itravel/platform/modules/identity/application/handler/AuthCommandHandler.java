package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.command.auth.LogoutCommand;
import com.itravel.platform.modules.identity.application.command.auth.RefreshCommand;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
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

    @Transactional
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

        if (AuthProvider.USERNAME.equals(account.getAuthProvider()) || AuthProvider.EMAIL.equals(account.getAuthProvider())) {
            if(!passwordEncoderAdapter.matches(command.password().value(),account.getPassword())){
                if(account.getAuthProvider().equals(AuthProvider.USERNAME)){
                    throw new UsernameOrPasswordInvalidException();
                } else{
                    throw new EmailOrPasswordInvalidException();
                }
            }
        } else {
            throw new InvalidLoginMethodException();
        }

        return createToken(account);
    }

    @Transactional
    public void logout(LogoutCommand command){
        String refreshTokenRaw = command.refreshToken();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);

        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository
                .findByTokenHash(refreshTokenHash);

        if(refreshTokenOpt.isEmpty()) {
            return;
        }

        RefreshToken refreshToken = refreshTokenOpt.get();
        refreshToken.verifyOwner(command.accountId());

        refreshToken.revoke();

        refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public AuthToken refresh(RefreshCommand command) throws JOSEException {
        // revoke old token
        String refreshTokenRaw = command.refreshToken();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);

        RefreshToken refreshToken = refreshTokenRepository.findByTokenHash(refreshTokenHash)
                .orElseThrow(RefreshTokenNotFoundException::new);

        refreshToken.verifyOwner(command.accountId());

        if(refreshToken.isRevoked()){
            throw new RefreshTokenRevokedException();
        }
        if (refreshToken.isExpired()) {
            throw new RefreshTokenExpiredException();
        }

        refreshToken.revoke();
        refreshTokenRepository.save(refreshToken);

        // create new token
        Account account = accountRepository.findById(refreshToken.getAccountId())
                .orElseThrow(AccountNotExistException::new);

        // check valid account
        if (AccountStatus.INACTIVE.equals(account.getStatus())) {
            throw new AccountInActiveException();
        }

        if (!Objects.isNull(account.getDeletedAt())) {
            throw new AccountDeletedException();
        }

        return createToken(account);
    }

    private AuthToken createToken(Account account) throws JOSEException {
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