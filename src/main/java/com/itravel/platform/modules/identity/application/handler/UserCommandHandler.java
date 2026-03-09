package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.account.CreateAccountByUserNameCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountCommand;
import com.itravel.platform.modules.identity.application.command.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.application.service.UserQueryService;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Avatar;
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
    AccountCommandHandler accountCommandHandler;
    MediaUploadPort mediaUploadPort;

    @Transactional
    public UserDetail CreateSystemUser(CreateUserCommand command) {
        Avatar avatar = null;
        if (command.avatar() != null && !command.avatar().isEmpty()) {
            String avatarUrl = mediaUploadPort.uploadAvatar(command.avatar());
            avatar = new Avatar(avatarUrl);
        }

        User user = User.create(command.fullName(), command.phoneNumber(), avatar, command.gender(),command.email(), command.dateOfBirth());

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
        user.updatePhoneNumber(command.phoneNumber());
        user.updateGender(command.gender());
        user.updateEmail(command.email());
        user.updateDateOfBirth(command.dateOfBirth());

        if (command.avatar() != null && !command.avatar().isEmpty()) {
            String avatarUrl = mediaUploadPort.uploadAvatar(command.avatar());
            user.updateAvatar(new Avatar(avatarUrl));
        }

        UpdateAccountPasswordCommand updateAccountPasswordCommand =
                new UpdateAccountPasswordCommand(
                        command.id().value(),
                        command.newPassword()
                );

        accountCommandHandler.ChangePassword(updateAccountPasswordCommand);

        UpdateAccountCommand updateAccountCommand =
                new UpdateAccountCommand(
                        command.id().value(),
                        command.status(),
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
        accountCommandHandler.delete(command.id().value());
        user.softDelete();
        userRepository.save(user);
    }

    @Transactional
    public void restore(RestoreUserCommand command){
        User user = userRepository.findById(command.id())
                .orElseThrow(UserNotExistException::new);
        accountCommandHandler.restore(command.id().value());
        user.restore();
        userRepository.save(user);
    }

    @Transactional
    public void destroy(DeleteUserCommand command){
        userRepository.destroy(command.id());
        accountCommandHandler.destroy(command.id().value());
    }
}
