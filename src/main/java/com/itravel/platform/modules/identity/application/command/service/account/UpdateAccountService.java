package com.itravel.platform.modules.identity.application.command.service.account;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.application.exception.AccountLinkNotExistException;
import com.itravel.platform.modules.identity.application.exception.AccountNotExistException;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.role.PermissionOverride;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.domain.account.AccountLink;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import com.itravel.platform.modules.identity.application.port.in.account.UpdateAccountUseCase;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateAccountService implements UpdateAccountUseCase {
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountLinkRepository accountLinkRepository;
    
    @Transactional
    public Account execute(UpdateAccountCommand command){
        AccountLink link = accountLinkRepository.findByTargetId(command.targetId())
                .orElseThrow(AccountLinkNotExistException::new);
        Account account = accountRepository.findById(link.getAccountId())
                .orElseThrow(AccountNotExistException::new);
        account.checkUpdate();
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

        account.changeStatus(command.status());

        accountRepository.save(account);
        return account;
    }    
}

