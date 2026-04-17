package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.nimbusds.jose.JOSEException;

public interface LoginUseCase {
    AuthTokenDTO execute(LoginCommand command) throws JOSEException;
}
