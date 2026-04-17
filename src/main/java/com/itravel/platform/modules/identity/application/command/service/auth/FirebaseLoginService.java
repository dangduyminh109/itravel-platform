package com.itravel.platform.modules.identity.application.command.service.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.itravel.platform.modules.identity.application.command.model.auth.FirebaseLoginCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.RegisterCustomerByGoogleCommand;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.itravel.platform.modules.identity.application.exception.InvalidFirebaseTokenException;
import com.itravel.platform.modules.identity.application.port.in.auth.FirebaseLoginUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.RegisterCustomerByGoogleUseCase;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.ProviderId;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.user.FullName;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FirebaseLoginService implements FirebaseLoginUseCase {
    AccountRepository accountRepository;
    RegisterCustomerByGoogleUseCase registerCustomerByGoogleUseCase;
    TokenApplicationService tokenApplicationService;

    @Override
    @Transactional
    public AuthTokenDTO execute(FirebaseLoginCommand command) {
        try {
            FirebaseToken decodedToken =
                    FirebaseAuth.getInstance().verifyIdToken(command.idToken());

            String email = decodedToken.getEmail();
            String uid = decodedToken.getUid();
            String name = decodedToken.getName();

            Account account = accountRepository.findByEmail(new Email(email))
                    .orElseGet(() -> {
                        RegisterCustomerByGoogleCommand registerCommand = new RegisterCustomerByGoogleCommand(
                                new Email(email),
                                new FullName(name),
                                new ProviderId(uid)
                        );
                        return registerCustomerByGoogleUseCase.execute(registerCommand);
                    });

            return tokenApplicationService.createToken(account);
        } catch (Exception e) {
            throw new InvalidFirebaseTokenException();
        }
    }
}
