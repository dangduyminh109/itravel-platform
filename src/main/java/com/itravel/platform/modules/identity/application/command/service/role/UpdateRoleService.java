package com.itravel.platform.modules.identity.application.command.service.role;

import com.itravel.platform.modules.identity.application.command.model.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.application.exception.RoleExistedException;
import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.application.port.in.role.UpdateRoleUseCase;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.domain.role.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateRoleService implements UpdateRoleUseCase {
    RoleRepository roleRepository;

    @Override
    @Transactional
    public Role execute(UpdateRoleCommand command) {
        Role role = roleRepository.findById(command.id())
                .orElseThrow(RoleNotExistException::new);

        if (!role.getName().equals(command.name())) {
            roleRepository.findByRoleName(command.name()).ifPresent(existing -> {
                throw new RoleExistedException();
            });
            role.rename(command.name());
        }

        role.changeStatus(command.status());
        return roleRepository.save(role);
    }
}
