package com.itravel.platform.modules.identity.domain.role;

import com.itravel.platform.modules.identity.domain.role.PermissionType;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.domain.role.Permission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PermissionOverride {
    final Long id;
    final AccountId accountId;
    final PermissionType permissionType;
    Permission permission;

    public static PermissionOverride create(AccountId accountId, PermissionType type, Permission permission) {
        return new PermissionOverride(null, accountId, type, permission);
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static PermissionOverride fromExisting(
            Long id,
            AccountId accountId,
            PermissionType type,
            Permission permission
    ) {
        PermissionOverride permissionOverride = new PermissionOverride(
                id, accountId, type, permission
        );
        return permissionOverride;
    }
}

