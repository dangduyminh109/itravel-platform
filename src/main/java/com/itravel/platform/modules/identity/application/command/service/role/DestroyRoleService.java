package com.itravel.platform.modules.identity.application.command.service.role;

import com.itravel.platform.modules.identity.application.command.model.role.DeleteRoleCommand;
import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.application.port.in.role.DestroyRoleUseCase;
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
public class DestroyRoleService implements DestroyRoleUseCase {
    RoleRepository roleRepository;

    @Override
    @Transactional
    public void execute(DeleteRoleCommand command) {
        Role role = roleRepository.findById(command.id())
                .orElseThrow(RoleNotExistException::new);
        role.checkUpdate();
        roleRepository.destroy(command.id());
    }
}
