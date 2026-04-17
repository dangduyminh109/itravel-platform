package com.itravel.platform.modules.identity.application.port.in.user.facade;
import com.itravel.platform.modules.identity.application.command.model.user.*;
import com.itravel.platform.modules.identity.application.port.in.user.*;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserCommandFacade {
    CreateUserUseCase createUserUseCase;
    UpdateUserUseCase updateUserUseCase;
    DeleteUserUseCase deleteUserUseCase;
    RestoreUserUseCase restoreUserUseCase;
    DestroyUserUseCase destroyUserUseCase;
    public UserDetailDTO create(CreateUserCommand command) { return createUserUseCase.execute(command); }
    public UserDetailDTO update(UpdateUserCommand command) { return updateUserUseCase.execute(command); }
    public void delete(DeleteUserCommand command) { deleteUserUseCase.execute(command); }
    public void restore(RestoreUserCommand command) { restoreUserUseCase.execute(command); }
    public void destroy(DeleteUserCommand command) { destroyUserUseCase.execute(command); }
}

