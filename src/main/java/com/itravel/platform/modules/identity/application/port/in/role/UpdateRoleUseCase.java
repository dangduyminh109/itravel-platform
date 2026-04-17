package com.itravel.platform.modules.identity.application.port.in.role;

import com.itravel.platform.modules.identity.application.command.model.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.domain.role.Role;

public interface UpdateRoleUseCase {
    Role execute(UpdateRoleCommand command);
}
