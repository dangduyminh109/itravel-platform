package com.itravel.platform.modules.identity.application.port.in.user;
import com.itravel.platform.modules.identity.application.command.model.user.UpdateUserCommand;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
public interface UpdateUserUseCase { UserDetailDTO execute(UpdateUserCommand command); }
