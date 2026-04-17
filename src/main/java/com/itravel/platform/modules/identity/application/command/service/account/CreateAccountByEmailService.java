package com.itravel.platform.modules.identity.application.command.service.account;
import com.itravel.platform.modules.identity.application.command.model.account.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.domain.role.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByEmailUseCase;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateAccountByEmailService implements CreateAccountByEmailUseCase {
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountLinkRepository accountLinkRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;
    
    public Account execute(CreateAccountByEmailCommand command) {
        accountRepository.findByEmail(command.email()).ifPresent(e -> {
            throw new EmailExistedException();
        });
        Role role = roleRepository.findByRoleName(new RoleName("customer"))
                .orElseThrow(RoleNotExistException::new);

        Account account = Account.createByEmail(command.email(), passwordEncoderAdapter.encode(command.password()), role);
        AccountLink accountLink = AccountLink.linkToCustomer(account.getId(), command.customerId().value());

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        return account;
    }
}

