package com.itravel.platform.modules.identity.application.service;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionType;
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
import java.util.Set;
import java.util.stream.Collectors;

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

                  return createResponse(user, account);
                }).toList();
    }

    public static UserDetail createResponse(User user, Account account){
        Set<PermissionOverrideCommand> permissionOverrides =
                account.getPermissionOverrides().stream()
                        .map(item ->
                                new PermissionOverrideCommand(
                                        item.getPermission(),
                                        item.getPermissionType())
                        )
                        .collect(Collectors.toSet());

        return UserDetail.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(account.getUsername())
                .roleList(account.getRoleList())
                .permissionOverrides(permissionOverrides)
                .status(account.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }
}
