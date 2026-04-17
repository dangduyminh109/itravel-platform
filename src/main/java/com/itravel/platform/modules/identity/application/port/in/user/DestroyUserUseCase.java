package com.itravel.platform.modules.identity.application.port.in.user;
import com.itravel.platform.modules.identity.application.command.model.user.DeleteUserCommand;
public interface DestroyUserUseCase { void execute(DeleteUserCommand command); }
