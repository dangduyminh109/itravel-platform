package com.itravel.platform.modules.identity.application.command.service.user;
import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByUserNameCommand;
import com.itravel.platform.modules.identity.application.command.model.user.CreateUserCommand;
import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;
import com.itravel.platform.modules.identity.domain.user.User;
import com.itravel.platform.modules.identity.domain.user.Avatar;
import com.itravel.platform.modules.identity.application.port.out.user.UserRepository;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.application.dto.PermissionOverrideDTO;
import com.itravel.platform.modules.identity.domain.role.Permission;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.user.CreateUserUseCase;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateUserService implements CreateUserUseCase {
    UserRepository userRepository;
    AccountCommandFacade accountCommandHandler;
    MediaUploadPort mediaUploadPort;
    
    @Transactional
    public UserDetailDTO execute(CreateUserCommand command) {
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
                .createByUserName(createAccountByUserNameCommand);

        userRepository.save(user);

        return UserDetailDTO.builder()
                .id(user.getId().value())
                .fullName(user.getFullName().value())
                .phoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber().value() : null)
                .avatar(user.getAvatar() != null ? user.getAvatar().value() : null)
                .gender(user.getGender() != null ? user.getGender().name() : null)
                .email(user.getEmail() != null ? user.getEmail().value() : null)
                .dateOfBirth(user.getDateOfBirth())
                .username(account.getUsername().value())
                .status(account.getStatus().name())
                .roleList(account.getRoleList().stream()
                        .map(r -> RoleDTO.builder()
                                .id(r.getId().value())
                                .name(r.getName().value())
                                .status(r.getStatus().name())
                                .permissionList(r.getPermissionList().stream()
                                        .map(Permission::code)
                                        .collect(Collectors.toSet()))
                                .build())
                        .collect(Collectors.toSet()))
                .permissionOverrides(account.getPermissionOverrides().stream()
                        .map(p -> PermissionOverrideDTO.builder()
                                .permission(p.getPermission().code())
                                .permissionType(p.getPermissionType().name())
                                .build())
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }
}

