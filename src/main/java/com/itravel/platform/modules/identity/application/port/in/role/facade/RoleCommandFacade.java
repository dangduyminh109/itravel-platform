package com.itravel.platform.modules.identity.application.port.in.role.facade;

import com.itravel.platform.modules.identity.application.command.model.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.DeleteRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.application.port.in.role.CreateRoleUseCase;
import com.itravel.platform.modules.identity.application.port.in.role.DestroyRoleUseCase;
import com.itravel.platform.modules.identity.application.port.in.role.UpdatePermissionForRoleUseCase;
import com.itravel.platform.modules.identity.application.port.in.role.UpdateRoleUseCase;
import com.itravel.platform.modules.identity.domain.role.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleCommandFacade {
    CreateRoleUseCase createRoleUseCase;
    UpdateRoleUseCase updateRoleUseCase;
    DestroyRoleUseCase destroyRoleUseCase;
    UpdatePermissionForRoleUseCase updatePermissionForRoleUseCase;

    public Role create(CreateRoleCommand command) {
        return createRoleUseCase.execute(command);
    }

    public Role update(UpdateRoleCommand command) {
        return updateRoleUseCase.execute(command);
    }

    public void destroy(DeleteRoleCommand command) {
        destroyRoleUseCase.execute(command);
    }

    public void updatePermissionForRole(List<UpdatePermissionForRoleCommand> commands) {
        updatePermissionForRoleUseCase.execute(commands);
    }
}
