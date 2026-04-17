package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.FirebaseLoginCommand;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;

public interface FirebaseLoginUseCase {
    AuthTokenDTO execute(FirebaseLoginCommand command);
}
