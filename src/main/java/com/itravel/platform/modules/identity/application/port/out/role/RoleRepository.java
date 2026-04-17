package com.itravel.platform.modules.identity.application.port.out.role;

import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.role.RoleId;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findById(RoleId roleId);
    List<Role> findAllById(List<RoleId> ids);
    Optional<Role> findByRoleName(RoleName roleName);
    Role save(Role role);
    void destroy(RoleId roleId);
}
