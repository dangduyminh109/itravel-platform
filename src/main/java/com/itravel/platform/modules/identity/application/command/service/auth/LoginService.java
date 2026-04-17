package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.port.in.auth.LoginUseCase;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.modules.identity.domain.account.AuthProvider;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
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
public class LoginService implements LoginUseCase {
    AccountRepository accountRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;
    TokenApplicationService tokenApplicationService;

    @Override
    @Transactional
    public AuthTokenDTO execute(LoginCommand command) throws JOSEException {
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
            if(!passwordEncoderAdapter.matches(command.password(), account.getPassword())){
                if(account.getAuthProvider().equals(AuthProvider.USERNAME)){
                    throw new UsernameOrPasswordInvalidException();
                } else{
                    throw new EmailOrPasswordInvalidException();
                }
            }
        } else {
            throw new InvalidLoginMethodException();
        }

        return tokenApplicationService.createToken(account);
    }
}
