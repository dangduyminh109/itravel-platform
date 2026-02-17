package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import com.itravel.platform.modules.identity.domain.exception.AdminRoleImmutableException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.util.HashSet;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role {
    RoleId id;
    RoleName name;
    RoleStatus status;
    final Set<Permission> permissionList = new HashSet<>();

    private Role(RoleName name, RoleStatus status) {
        this.id = null;
        this.name = name;
        this.status = status;
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Role fromExisting (
            RoleId id,
            RoleName name,
            RoleStatus status,
            Set<Permission> permissionList
    ) {
        Role r = new Role(id, name, status);
        r.permissionList.addAll(permissionList);
        return r;
    }
    public static Role create(RoleName name, RoleStatus status){
        return new Role(name,status);
    }
    public void rename(RoleName name) {
        if(this.name.value().equals("admin")){
            throw new AdminRoleImmutableException();
        }
        this.name = name;
    }
    public void changeStatus(RoleStatus status) {
        if(this.name.value().equals("admin")){
            throw new AdminRoleImmutableException();
        }
        this.status = status;
    }
    public void grantPermission(Permission permission) {
        if(this.name.value().equals("admin")){
            throw new AdminRoleImmutableException();
        }
        this.permissionList.add(permission);
    }

    public void revokePermission(Permission permission) {
        if(this.name.value().equals("admin")){
            throw new AdminRoleImmutableException();
        }
        this.permissionList.remove(permission);
    }
}
