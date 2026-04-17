package com.itravel.platform.modules.identity.application.command.model.user;

import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.modules.identity.domain.user.Gender;
import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.role.*;
import com.itravel.platform.modules.identity.domain.account.*;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

public record UpdateUserCommand(
        UserId id,
        FullName fullName,
        PhoneNumber phoneNumber,
        MultipartFile avatar,
        Gender gender,
        Email email,
        LocalDate dateOfBirth,
        AccountStatus status,
        RawPassword newPassword,
        Set<RoleId> roleList,
        Set<com.itravel.platform.modules.identity.application.command.model.account.PermissionOverrideCommand> permissionOverrides,
        boolean removeAvatar
) {
}
