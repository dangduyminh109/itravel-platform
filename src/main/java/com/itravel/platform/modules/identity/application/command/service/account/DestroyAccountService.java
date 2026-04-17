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
import java.util.List;
import com.itravel.platform.modules.identity.application.port.in.account.DestroyAccountUseCase;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyAccountService implements DestroyAccountUseCase {
    AccountRepository accountRepository;
    AccountLinkRepository accountLinkRepository;
    
    @Transactional
    public void execute(DeleteAccountCommand command){
        AccountLink accountLink = accountLinkRepository.findByTargetId(command.targetId())
                .orElseThrow(AccountLinkNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(AccountNotExistException::new);

        account.checkUpdate();
        accountLinkRepository.destroy(accountLink.getId());
        List<AccountLink> remainingLinks = accountLinkRepository.findAllByAccountId(account.getId());
        if (remainingLinks.isEmpty()) {
            accountRepository.destroy(account.getId());
        }
    }

}

