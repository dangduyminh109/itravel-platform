package com.itravel.platform.modules.identity.application.command.service.account;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.exception.AccountLinkNotExistException;
import com.itravel.platform.modules.identity.application.exception.AccountNotExistException;
import com.itravel.platform.modules.identity.application.port.in.account.ChangePasswordUseCase;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.AccountLink;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangePasswordService implements ChangePasswordUseCase {
    AccountRepository accountRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;
    AccountLinkRepository accountLinkRepository;

    @Override
    @Transactional
    public void execute(UpdateAccountPasswordCommand command) {
        AccountLink link = accountLinkRepository.findByTargetId(command.targetId())
                .orElseThrow(AccountLinkNotExistException::new);
        Account account = accountRepository.findById(link.getAccountId())
                .orElseThrow(AccountNotExistException::new);
        if(command.newPassword() == null){
            return;
        }

        account.changePassword(passwordEncoderAdapter.encode(command.newPassword()));
        accountRepository.save(account);
    }
}

