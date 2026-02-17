package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findById(RoleId roleId);
    List<Role> findAllById(List<RoleId> roleIds);
    Optional<Role> findByRoleName(RoleName roleName);
    List<Role> getRoles();
    Role save(Role role);
    void destroy(RoleId roleId);
}
