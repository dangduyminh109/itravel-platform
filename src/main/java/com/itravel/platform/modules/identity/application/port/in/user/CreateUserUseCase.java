package com.itravel.platform.modules.identity.application.port.in.user;
import com.itravel.platform.modules.identity.application.command.model.user.CreateUserCommand;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
public interface CreateUserUseCase { UserDetailDTO execute(CreateUserCommand command); }
