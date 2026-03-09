package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

public record CreateUserCommand(
        Username username,
        RawPassword password,
        FullName fullName,
        PhoneNumber phoneNumber,
        MultipartFile avatar,
        Gender gender,
        Email email,
        LocalDate dateOfBirth,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides
) {}
