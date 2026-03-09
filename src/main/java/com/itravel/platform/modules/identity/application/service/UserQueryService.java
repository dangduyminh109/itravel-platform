package com.itravel.platform.modules.identity.application.service;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.common.utils.SecurityUtils;
import com.itravel.platform.modules.identity.api.dto.response.UserResponse;
import com.itravel.platform.modules.identity.api.mapper.UserRestMapper;
import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.application.exception.AccountInActiveException;
import com.itravel.platform.modules.identity.application.exception.UserDeletedException;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.UserRepository;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserQueryService {
    UserRepository userRepository;
    AccountLinkRepository accountLinkRepository;
    AccountRepository accountRepository;
    UserRestMapper mapper;

    public PageResponse<UserResponse> getUsers(String keyword, Pageable pageable,boolean isDeleted) {
        Page<User> userPage = userRepository.getUsers(keyword, pageable, isDeleted);

        return PageResponse.<UserResponse>builder()
                .currentPage(userPage.getNumber())
                .pageSize(userPage.getSize())
                .totalElements(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .data(
                        userPage.getContent().stream()
                                .map(user -> {
                                    AccountLink accountLink = accountLinkRepository
                                            .findByTargetId(user.getId().value())
                                            .orElseThrow(UserNotExistException::new);
                                    Account account = accountRepository.findById(accountLink.getAccountId())
                                            .orElseThrow(UserNotExistException::new);

                                    return mapper.toUserResponse(createResponse(user, account));
                                }).toList()
                )
                .build();
    }

    public UserResponse getUser(UserId id) {
        AccountLink accountLink = accountLinkRepository
                .findByTargetId(id.value())
                .orElseThrow(UserNotExistException::new);
        User user = userRepository.findById(id)
                .orElseThrow(UserNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(UserNotExistException::new);

        return mapper.toUserResponse(createResponse(user, account));
    }

    public UserDetail getMe() {
        String accountId = SecurityUtils.getCurrentAccountId();
        AccountLink accountLink = accountLinkRepository
                .findByAccountId(new AccountId(accountId))
                .orElseThrow(UserNotExistException::new);
        User user = userRepository.findById(new UserId(accountLink.getTargetId()))
                .orElseThrow(UserNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(UserNotExistException::new);

        if(user.getDeletedAt() != null || account.getDeletedAt() != null){
            throw new UserDeletedException();
        }
        if(AccountStatus.INACTIVE.equals(account.getStatus())){
            throw new AccountInActiveException();
        }

        return createResponse(user, account);
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
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
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
