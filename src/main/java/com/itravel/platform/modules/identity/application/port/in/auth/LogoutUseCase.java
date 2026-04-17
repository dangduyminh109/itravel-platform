package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.LogoutCommand;

public interface LogoutUseCase {
    void execute(LogoutCommand command);
}
