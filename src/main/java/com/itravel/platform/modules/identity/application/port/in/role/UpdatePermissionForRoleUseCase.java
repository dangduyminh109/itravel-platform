package com.itravel.platform.modules.identity.application.port.in.role;

import com.itravel.platform.modules.identity.application.command.model.role.UpdatePermissionForRoleCommand;

import java.util.List;

public interface UpdatePermissionForRoleUseCase {
    void execute(List<UpdatePermissionForRoleCommand> commands);
}
