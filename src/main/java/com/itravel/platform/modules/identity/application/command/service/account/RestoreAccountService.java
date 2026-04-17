package com.itravel.platform.modules.identity.application.command.service.account;
import com.itravel.platform.modules.identity.application.command.model.account.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.account.RestoreAccountUseCase;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestoreAccountService implements RestoreAccountUseCase {
    AccountRepository accountRepository;
    AccountLinkRepository accountLinkRepository;

    @Transactional
    public void execute(RestoreAccountCommand command){
        AccountLink accountLink = accountLinkRepository.findByTargetId(command.targetId())
                .orElseThrow(AccountLinkNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(AccountNotExistException::new);

        if(account.getUsername() != null && "admin".equals(account.getUsername().value())){
            throw new UserNotDeleteOrUpdateException();
        }
        account.checkUpdate();
        account.restore();
        accountRepository.save(account);
    }
}

