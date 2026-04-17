package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.application.dto.AccountDTO;
import com.itravel.platform.modules.identity.application.port.out.account.AccountQueryPort;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.AccountMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountJpaRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountLinkJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryAdapter implements AccountQueryPort {
    AccountJpaRepository accountJpaRepository;
    AccountLinkJpaRepository accountLinkJpaRepository;

    @Override
    public Optional<AccountDTO> getAccount(String targetId) {
        return accountLinkJpaRepository.findByTargetId(targetId)
                .flatMap(link -> accountJpaRepository.findById(link.getAccountId()))
                .map(AccountMapper::toAccountDTO);
    }

    @Override
    public Optional<AccountDTO> getAccountByAccountId(String accountId) {
        return accountJpaRepository.findById(accountId)
                .map(AccountMapper::toAccountDTO);
    }
}
