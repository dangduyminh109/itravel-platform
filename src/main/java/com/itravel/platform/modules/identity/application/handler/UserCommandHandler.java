package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.account.CreateAccountByUserNameCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.exception.UserNotDeleteOrUpdateException;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.application.service.AccountQueryService;
import com.itravel.platform.modules.identity.application.service.UserQueryService;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserCommandHandler {
    UserRepository userRepository;
    AccountQueryService accountQueryService;
    AccountCommandHandler accountCommandHandler;

    @Transactional
    public UserDetail CreateSystemUser(CreateUserCommand command) {
        User user = User.create(command.fullName());

        CreateAccountByUserNameCommand createAccountByUserNameCommand
                = new CreateAccountByUserNameCommand( command.username(),
                command.password(),
                command.roleList(),
                command.permissionOverrides(),
                user.getId()
        );

        Account account = accountCommandHandler
                .CreateByUserName(createAccountByUserNameCommand);

        userRepository.save(user);

        return UserQueryService.createResponse(user,account);
    }

    @Transactional
    public UserDetail update(UpdateUserCommand command){
        User user = userRepository.findById(command.id())
                .orElseThrow(UserNotExistException::new);
        user.updateName(command.fullName());

        UpdateAccountPasswordCommand updateAccountPasswordCommand =
                new UpdateAccountPasswordCommand(
                        command.id().value(),
                        command.newPassword()
                );

        accountCommandHandler.ChangePassword(updateAccountPasswordCommand);

        UpdateAccountCommand updateAccountCommand =
                new UpdateAccountCommand(
                        command.id().value(),
                        command.roleList(),
                        command.permissionOverrides()
                );

        Account account = accountCommandHandler.update(updateAccountCommand);

        userRepository.save(user);
        return UserQueryService.createResponse(user,account);
    }

    @Transactional
    public void delete(DeleteUserCommand command){
        Optional<User> optionalUser = userRepository.findById(command.id());
        if(optionalUser.isEmpty()){
            return;
        }
        User user = optionalUser.get();
        Account account = accountQueryService.getAccount(command.id().value());
        if(account.getUsername().value().equals("admin")){
            throw new UserNotDeleteOrUpdateException();
        }
        user.softDelete();
        userRepository.save(user);
    }

    @Transactional
    public void restore(RestoreUserCommand command){
        User user = userRepository.findById(command.id())
                .orElseThrow(UserNotExistException::new);
        user.restore();
        userRepository.save(user);
    }

    @Transactional
    public void destroy(DeleteUserCommand command){
        userRepository.destroy(command.id());
        accountCommandHandler.destroy(command.id().value());
    }
}
