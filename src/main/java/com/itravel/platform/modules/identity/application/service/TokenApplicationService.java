package com.itravel.platform.modules.identity.application.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.itravel.platform.modules.identity.application.command.auth.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.query.AuthToken;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountLinkType;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.repository.RefreshTokenRepository;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenGenerator;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import com.itravel.platform.modules.identity.domain.service.TokenProvider;
import com.nimbusds.jose.JOSEException;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenApplicationService {
    TokenProvider tokenProvider;
    RefreshTokenGenerator refreshTokenGenerator;
    RefreshTokenHasher refreshTokenHasher;
    RefreshTokenRepository refreshTokenRepository;

    @NonFinal
    @Value("${security.refresh-token.refreshable-duration}")
    long REFRESH_DURATION;

    public AuthToken createToken(Account account) throws JOSEException {
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