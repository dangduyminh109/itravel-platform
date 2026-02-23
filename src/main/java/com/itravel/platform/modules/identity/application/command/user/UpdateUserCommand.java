package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

public record UpdateUserCommand(
        UserId id,
        FullName fullName,
        PhoneNumber phoneNumber,
        MultipartFile avatar,
        Gender gender,
        LocalDate dateOfBirth,
        AccountStatus status,
        RawPassword newPassword,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides
) {
}
