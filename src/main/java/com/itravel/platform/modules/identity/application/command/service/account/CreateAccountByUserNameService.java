package com.itravel.platform.modules.identity.application.command.service.account;
import com.itravel.platform.modules.identity.application.command.model.account.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.role.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.domain.customer.*;
import com.itravel.platform.modules.identity.domain.otp.*;
import com.itravel.platform.modules.identity.domain.auth.*;
import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByUserNameUseCase;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateAccountByUserNameService implements CreateAccountByUserNameUseCase {
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountLinkRepository accountLinkRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;
    
    public Account execute(CreateAccountByUserNameCommand command) {
        accountRepository.findByUsername(command.username()).ifPresent(e -> {
            throw new UsernameExistedException();
        });

        Account account = Account.createByUsername(command.username(), passwordEncoderAdapter.encode(command.password()));
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
}

