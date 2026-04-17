package com.itravel.platform.modules.identity.application.command.service.role;

import com.itravel.platform.modules.identity.application.command.model.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.application.exception.RoleExistedException;
import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.application.port.in.role.UpdatePermissionForRoleUseCase;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.modules.identity.domain.role.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdatePermissionForRoleService implements UpdatePermissionForRoleUseCase {
    RoleRepository roleRepository;

    @Override
    @Transactional
    public void execute(List<UpdatePermissionForRoleCommand> commands) {
        for (UpdatePermissionForRoleCommand command : commands) {
            Role role = roleRepository.findById(command.id())
                    .orElseThrow(RoleNotExistException::new);

            if (!role.getName().equals(command.name())) {
                roleRepository.findByRoleName(command.name()).ifPresent(existing -> {
                    throw new RoleExistedException();
                });
                role.rename(command.name());
            }

            role.changeStatus(command.status());

            Set<Permission> newPermissions = new HashSet<>(command.permissionCodeList());
            Set<Permission> oldPermissions = role.getPermissionList();

            for (Permission p : new HashSet<>(oldPermissions)) {
                if (!newPermissions.contains(p)) {
                    role.revokePermission(p);
                }
            }

            for (Permission p : new HashSet<>(newPermissions)) {
                if (!oldPermissions.contains(p)) {
                    role.grantPermission(p);
                }
            }

            roleRepository.save(role);
        }
    }
}
