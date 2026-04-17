package com.itravel.platform.modules.identity.application.port.in.role;

import com.itravel.platform.modules.identity.application.command.model.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.domain.role.Role;

public interface CreateRoleUseCase {
    Role execute(CreateRoleCommand command);
}
