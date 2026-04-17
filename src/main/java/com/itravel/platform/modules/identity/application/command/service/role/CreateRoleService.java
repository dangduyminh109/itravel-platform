package com.itravel.platform.modules.identity.application.command.service.role;

import com.itravel.platform.modules.identity.application.command.model.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.exception.RoleExistedException;
import com.itravel.platform.modules.identity.application.port.in.role.CreateRoleUseCase;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.modules.identity.domain.role.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateRoleService implements CreateRoleUseCase {
    RoleRepository roleRepository;

    @Override
    @Transactional
    public Role execute(CreateRoleCommand command) {
        roleRepository.findByRoleName(command.name()).ifPresent(existing -> {
            throw new RoleExistedException();
        });

        Role role = Role.create(command.name(), command.status());
        if (command.permissionCodeList() != null) {
            for (Permission permission : command.permissionCodeList()) {
                role.grantPermission(permission);
            }
        }
        return roleRepository.save(role);
    }
}
