package com.itravel.platform.modules.identity.application.port.in.user;
import com.itravel.platform.modules.identity.application.command.model.user.RestoreUserCommand;
public interface RestoreUserUseCase { void execute(RestoreUserCommand command); }
