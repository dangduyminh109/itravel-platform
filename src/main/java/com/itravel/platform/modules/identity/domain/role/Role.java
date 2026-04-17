package com.itravel.platform.modules.identity.domain.role;

import com.itravel.platform.modules.identity.domain.role.RoleStatus;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.modules.identity.domain.role.RoleId;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import com.itravel.platform.modules.identity.domain.role.exception.RoleImmutableException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseAggregate<RoleId> {
    RoleName name;
    RoleStatus status;

    final Set<Permission> permissionList = new HashSet<>();

    private Role(RoleName name, RoleStatus status) {
        super(null);
        this.name = name;
        this.status = status;
    }

    private Role(RoleId id, RoleName name, RoleStatus status, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.status = status;
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Role fromExisting (
            RoleId id,
            RoleName name,
            RoleStatus status,
            Set<Permission> permissionList,
            Instant createdAt,
            Instant updatedAt
    ) {
        Role r = new Role(id, name, status, createdAt, updatedAt);
        r.permissionList.addAll(permissionList);
        return r;
    }
    public void checkUpdate(){
        if(this.name.value().equals("admin") || this.name.value().equals("customer") ){
            throw new RoleImmutableException();
        }
    }

    public static Role create(RoleName name, RoleStatus status){
        return new Role(name,status);
    }

    public void rename(RoleName name) {
        checkUpdate();
        this.name = name;
        touch();
    }
    public void changeStatus(RoleStatus status) {
        checkUpdate();
        this.status = status;
        touch();
    }
    public void grantPermission(Permission permission) {
        checkUpdate();
        this.permissionList.add(permission);
        touch();
    }

    public void revokePermission(Permission permission) {
        checkUpdate();
        this.permissionList.remove(permission);
        touch();
    }
}

