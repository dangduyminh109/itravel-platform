package com.itravel.platform.modules.identity.application.command.service.account;

import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByGoogleCommand;
import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByGoogleUseCase;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.AccountLink;
import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateAccountByGoogleService implements CreateAccountByGoogleUseCase {
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountLinkRepository accountLinkRepository;

    @Override
    @Transactional
    public Account execute(CreateAccountByGoogleCommand command) {
        Role role = roleRepository.findByRoleName(new RoleName("customer"))
                .orElseThrow(RoleNotExistException::new);

        Account account = Account.createByGoogle(command.email(), role, command.providerId());
        AccountLink accountLink = AccountLink.linkToSystemUser(account.getId(), command.customerId().value());

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        return account;
    }
}
