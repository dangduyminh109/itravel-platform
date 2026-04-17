package com.itravel.platform.modules.identity.application.command.model.user;

import com.itravel.platform.modules.identity.domain.user.Gender;
import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.role.*;
import com.itravel.platform.modules.identity.domain.account.*;

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
        Set<com.itravel.platform.modules.identity.application.command.model.account.PermissionOverrideCommand> permissionOverrides
) {}
