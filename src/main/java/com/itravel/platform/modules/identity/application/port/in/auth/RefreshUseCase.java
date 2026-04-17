package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.RefreshCommand;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.nimbusds.jose.JOSEException;

public interface RefreshUseCase {
    AuthTokenDTO execute(RefreshCommand command) throws JOSEException;
}
