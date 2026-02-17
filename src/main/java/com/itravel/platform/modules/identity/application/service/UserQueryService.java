package com.itravel.platform.modules.identity.application.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserQueryService {
    UserRepository userRepository;
    AccountLinkRepository accountLinkRepository;
    AccountRepository accountRepository;

    public List<UserDetail> getUsers(){
        return userRepository.getUsers().stream()
                .map(user -> {
                    AccountLink accountLink = accountLinkRepository
                            .findByTargetId(user.getId().value())
                            .orElseThrow(UserNotExistException::new);
                    Account account = accountRepository.findById(accountLink.getAccountId())
                            .orElseThrow(UserNotExistException::new);
                    return UserDetail.builder()
                            .id(user.getId())
                            .fullName(user.getFullName())
                            .username(account.getUsername())
                            .roleList(account.getRoleList())
                            .createdAt(user.getCreatedAt())
                            .updatedAt(user.getUpdatedAt())
                            .deletedAt(user.getDeletedAt())
                            .build();
                }).toList();
    }
}
