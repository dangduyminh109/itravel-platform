package com.itravel.platform.modules.identity.application.command.service.account;
import com.itravel.platform.modules.identity.application.command.model.account.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.domain.account.AccountLink;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.account.ChangeStatusUseCase;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeStatusService implements ChangeStatusUseCase {
    AccountRepository accountRepository;
    AccountLinkRepository accountLinkRepository;

    
    @Transactional
    public Account execute(UpdateAccountStatusCommand command){
        AccountLink link = accountLinkRepository.findByTargetId(command.targetId())
                .orElseThrow(AccountLinkNotExistException::new);
        Account account = accountRepository.findById(link.getAccountId())
                .orElseThrow(AccountNotExistException::new);
        account.changeStatus(command.newStatus());
        accountRepository.save(account);
        return account;
    }
}

