package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.RefreshCommand;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.port.in.auth.RefreshUseCase;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.application.port.out.auth.RefreshTokenRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.modules.identity.domain.auth.RefreshToken;
import com.itravel.platform.modules.identity.domain.auth.TokenHash;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefreshService implements RefreshUseCase {
    RefreshTokenHasher refreshTokenHasher;
    RefreshTokenRepository refreshTokenRepository;
    AccountRepository accountRepository;
    TokenApplicationService tokenApplicationService;

    @Override
    @Transactional
    public AuthTokenDTO execute(RefreshCommand command) throws JOSEException {
        // revoke old token
        String refreshTokenRaw = command.refreshToken();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);

        RefreshToken refreshToken = refreshTokenRepository.findByTokenHash(refreshTokenHash)
                .orElseThrow(RefreshTokenNotFoundException::new);

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

        return tokenApplicationService.createToken(account);
    }
}
