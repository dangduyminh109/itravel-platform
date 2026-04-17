package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.LogoutCommand;
import com.itravel.platform.modules.identity.application.port.in.auth.LogoutUseCase;
import com.itravel.platform.modules.identity.application.port.out.auth.RefreshTokenRepository;
import com.itravel.platform.modules.identity.domain.auth.RefreshToken;
import com.itravel.platform.modules.identity.domain.auth.TokenHash;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogoutService implements LogoutUseCase {
    RefreshTokenHasher refreshTokenHasher;
    RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void execute(LogoutCommand command) {
        String refreshTokenRaw = command.refreshToken();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);

        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository
                .findByTokenHash(refreshTokenHash);

        if(refreshTokenOpt.isEmpty()) {
            return;
        }

        RefreshToken refreshToken = refreshTokenOpt.get();

        refreshToken.revoke();

        refreshTokenRepository.save(refreshToken);
    }
}
