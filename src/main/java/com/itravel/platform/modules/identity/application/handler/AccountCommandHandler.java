package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.account.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.service.AccountQueryService;
import com.itravel.platform.modules.identity.domain.aggregate.*;
import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionType;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.RoleRepository;
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
public class AccountCommandHandler {
    AccountRepository accountRepository;
    AccountLinkRepository accountLinkRepository;
    RoleRepository roleRepository;
    AccountQueryService accountQueryService;
    PasswordEncoderAdapter passwordEncoderAdapter;

    @Transactional
    public Account CreateByUserName(CreateAccountByUserNameCommand command) {
        accountRepository.findByUsername(command.username()).ifPresent(e -> {
            throw new UsernameExistedException();
        });

        Account account = Account.createByUsername(command.username(), passwordEncoderAdapter.encode(command.password().value()));
        AccountLink accountLink = AccountLink.linkToSystemUser(account.getId(), command.id().value());

        Set<Role> roleList = new HashSet<>(roleRepository.findAllById(command.roleList().stream().toList()));

        for (Role role : roleList) {
            account.grantRole(role);
        }

        Set<PermissionOverrideCommand> permissionOverrideList = command.permissionOverrides() == null
                ? Set.of()
                : command.permissionOverrides();
        for (PermissionOverrideCommand item : permissionOverrideList){
            PermissionOverride permissionOverride =
                    PermissionOverride.create(
                            account.getId(),
                            item.permissionType(),
                            item.permission()
                    );
            account.grantPermissionOverride(permissionOverride);
        }
        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        return account;
    }

    public Account CreateByEmail(CreateAccountByEmailCommand command) {
        accountRepository.findByEmail(command.email()).ifPresent(e -> {
            throw new EmailExistedException();
        });
        Role role = roleRepository.findByRoleName(new RoleName("customer"))
                .orElseThrow(RoleNotExistException::new);

        Account account = Account.createByEmail(command.email(), passwordEncoderAdapter.encode(command.password().value()),role);
        AccountLink accountLink = AccountLink.linkToCustomer(account.getId(), command.customerId().value());

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        return account;
    }

    public Account CreateByGoogle(CreateAccountByGoogleCommand command) {
        Role role = roleRepository.findByRoleName(new RoleName("customer"))
                .orElseThrow(RoleNotExistException::new);

        Account account = Account.createByGoogle(command.email(),role);

        AccountLink accountLink = AccountLink.linkToSystemUser(account.getId(), command.customerId().value());

        accountRepository.save(account);
        accountLinkRepository.save(accountLink);
        return account;
    }

    @Transactional
    public Account ChangePassword(UpdateAccountPasswordCommand command){
        Account account = accountQueryService.getAccount(command.targetId());
        if(command.newPassword() == null){
            return account;
        }
        account.changePassword(passwordEncoderAdapter.encode(command.newPassword().value()));
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account update(UpdateAccountCommand command){
        Account account = accountQueryService.getAccount(command.targetId());

        if("admin".equals(account.getUsername().value())){
            throw new UserNotDeleteOrUpdateException();
        }

        Set<Role> newRoles = new HashSet<>(roleRepository.findAllById(command.roleList().stream().toList()));
        Set<Role> oldRoles = new HashSet<>(account.getRoleList());

        // revoke
        for (Role p : new HashSet<>(oldRoles)) {
            if (!newRoles.contains(p)) account.revokeRole(p);
        }

        // grant
        for (Role p : new HashSet<>(newRoles)) {
            if (!oldRoles.contains(p)) account.grantRole(p);
        }

        // sync permission override
        account.syncPermissionOverride();

        Set<PermissionOverrideCommand> newOverrides = command.permissionOverrides() == null
                ? Set.of()
                : command.permissionOverrides();

        Set<PermissionOverride> oldOverrides =
                new HashSet<>(account.getPermissionOverrides());

        // So sánh theo permission + type
        // revoke
        for (PermissionOverride old : oldOverrides) {
            boolean stillExists = newOverrides.stream().anyMatch(cmd ->
                    cmd.permission().equals(old.getPermission()) &&
                            cmd.permissionType().equals(old.getPermissionType())
            );

            if (!stillExists) {
                account.revokePermissionOverride(old);
            }
        }

        // grant
        for (PermissionOverrideCommand cmd : newOverrides) {
            // kiểm tra trong override list
            boolean alreadyExists = oldOverrides.stream().anyMatch(old ->
                    old.getPermission().equals(cmd.permission()) &&
                            old.getPermissionType().equals(cmd.permissionType())
            );

            if (!alreadyExists) {
                account.grantPermissionOverride(
                        PermissionOverride.create(
                                account.getId(),
                                cmd.permissionType(),
                                cmd.permission()
                        )
                );
            }
        }

        accountRepository.save(account);
        return account;
    }

    @Transactional
    public void destroy(String targetId){
        AccountLink accountLink = accountLinkRepository.findByTargetId(targetId)
                .orElseThrow(AccountNotExistException::new);
        Account account = accountRepository.findById(accountLink.getAccountId())
                .orElseThrow(UserNotExistException::new);

        if("admin".equals(account.getUsername().value())){
            throw new UserNotDeleteOrUpdateException();
        }

        accountLinkRepository.destroy(accountLink.getId());
        Optional<AccountLink> link = accountLinkRepository.findByAccountId(account.getId());
        if (link.isEmpty()) {
            accountRepository.destroy(account.getId());
        }
    }
}