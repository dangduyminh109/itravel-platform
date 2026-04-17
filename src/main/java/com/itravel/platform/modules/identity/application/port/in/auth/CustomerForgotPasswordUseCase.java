package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.CustomerForgotPasswordCommand;

public interface CustomerForgotPasswordUseCase {
    String execute(CustomerForgotPasswordCommand command);
}
