package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.exception.UserNotDeleteOrUpdateException;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.exception.UsernameExistedException;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.RoleRepository;
import com.itravel.platform.modules.identity.domain.repository.UserRepository;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserCommandHandler {
    AccountRepository accountRepository;
    AccountLinkRepository accountLinkRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;

    @Transactional
    public UserDetail CreateSystemUser(CreateUserCommand command) {
        accountRepository.findByUsername(command.username()).ifPresent(e -> {
            throw new UsernameExistedException();
        });

        Account account = Account.createByUsername(command.username(), passwordEncoderAdapter.encode(command.password().value()));
        User user = User.create(command.fullName());
        AccountLink accountLink = AccountLink.linkToSystemUser(account.getId(), user.getId().value());

        Set<Role> roleList = new HashSet<>(roleRepository.findAllById(command.roleList().stream().toList()));
        for (Role role : roleList) {
            user.grantRole(role);
        }

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        userRepository.save(user);
        return UserDetail.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(account.getUsername())
                .roleList(user.getRoleList())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    @Transactional
    public UserDetail update(UpdateUserCommand command){
        User user = userRepository.findById(command.userId())
                .orElseThrow(UserNotExistException::new);

        AccountLink accountLink = accountLinkRepository
                .findByTargetId(user.getId().value())
                .orElseThrow(UserNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(UserNotExistException::new);

        if(account.getUsername().value().equals("admin")){
            throw new UserNotDeleteOrUpdateException();
        }
        account.changePassword(passwordEncoderAdapter.encode(command.password().value()));

        user.updateName(command.fullName());

        Set<Role> newRoles = new HashSet<>(roleRepository.findAllById(command.roleList().stream().toList()));
        Set<Role> oldRoles = new HashSet<>(user.getRoleList());

        // revoke
        for (Role p : new HashSet<>(oldRoles)) {
            if (!newRoles.contains(p)) user.revokeRole(p);
        }

        // grant
        for (Role p : new HashSet<>(newRoles)) {
            if (!oldRoles.contains(p)) user.grantRole(p);
        }
        accountRepository.save(account);
        userRepository.save(user);
        return UserDetail.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(account.getUsername())
                .roleList(user.getRoleList())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    @Transactional
    public void delete(DeleteUserCommand command){
        Optional<User> optionalUser = userRepository.findById(command.userId());
        if(optionalUser.isEmpty()){
            return;
        }
        User user = optionalUser.get();

        AccountLink accountLink = accountLinkRepository.findByTargetId(command.userId().value())
                .orElseThrow(UserNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(UserNotExistException::new);
        if(account.getUsername().value().equals("admin")){
            throw new UserNotDeleteOrUpdateException();
        }
        user.softDelete();
        userRepository.save(user);
    }

    @Transactional
    public void restore(RestoreUserCommand command){
        User user = userRepository.findById(command.userId())
                .orElseThrow(UserNotExistException::new);
        user.restore();
        userRepository.save(user);
    }

    @Transactional
    public void destroy(DeleteUserCommand command){
        userRepository.destroy(command.userId());
        AccountLink accountLink = accountLinkRepository.findByTargetId(command.userId().value())
                        .orElseThrow(UserNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(UserNotExistException::new);

        if(account.getUsername().value().equals("admin")){
            throw new UserNotDeleteOrUpdateException();
        }

        accountRepository.destroy(accountLink.getAccountId());
        accountLinkRepository.destroy(accountLink.getId());
    }
}
