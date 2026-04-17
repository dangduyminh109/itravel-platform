package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;
import com.itravel.platform.modules.identity.application.port.out.accountlink.AccountLinkQueryPort;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.AccountLinkMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountLinkJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLinkQueryAdapter implements AccountLinkQueryPort {
    AccountLinkJpaRepository repository;

    @Override
    public Optional<AccountLinkDTO> getByTargetId(String targetId) {
        return repository.findByTargetId(targetId)
                .map(AccountLinkMapper::toAccountLinkDTO);
    }

    @Override
    public Optional<AccountLinkDTO> getByAccountId(String accountId) {
        return repository.findByAccountId(accountId)
                .map(AccountLinkMapper::toAccountLinkDTO);
    }

    @Override
    public List<AccountLinkDTO> getAllByAccountId(String accountId) {
        return repository.findAllByAccountId(accountId).stream()
                .map(AccountLinkMapper::toAccountLinkDTO)
                .collect(Collectors.toList());
    }
}
