package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.DeleteRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.application.exception.AdminRoleCanNotDeleteException;
import com.itravel.platform.modules.identity.application.exception.RoleExistedException;
import com.itravel.platform.modules.identity.application.exception.RoleNotExistException;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleCommandHandler {
    RoleRepository roleRepository;

    @Transactional
    public Role create(CreateRoleCommand command){
        roleRepository.findByRoleName(command.name()).ifPresent(e -> {
            throw new RoleExistedException();
        });
        Role role = Role.create(command.name(),command.status());
        Set<Permission> permissions = new HashSet<>(command.permissionCodeList());
        for (Permission p : new HashSet<>(permissions)) {
            role.grantPermission(p);
        }
        return roleRepository.save(role);
    }

    @Transactional
    public Role update(UpdateRoleCommand command){
        Role role = roleRepository.findById(command.id())
                .orElseThrow(RoleNotExistException::new);
        if(!role.getName().equals(command.name())){
            roleRepository.findByRoleName(command.name()).ifPresent(e -> {
                throw new RoleExistedException();
            });
            role.rename(command.name());
        }
        role.changeStatus(command.status());
        roleRepository.save(role);
        return role;
    }

    @Transactional
    public void destroy(DeleteRoleCommand command){
        Role role = roleRepository.findById(command.id())
                .orElseThrow(RoleNotExistException::new);
       role.checkUpdate();
        roleRepository.destroy(command.id());
    }

    @Transactional
    public void updatePermissionForRole(List<UpdatePermissionForRoleCommand> updatePermissionForRoleCommands){
        for (UpdatePermissionForRoleCommand command : updatePermissionForRoleCommands) {
            Role role = roleRepository.findById(command.id())
                    .orElseThrow(RoleNotExistException::new);
            if(!role.getName().equals(command.name())){
                roleRepository.findByRoleName(command.name()).ifPresent(e -> {
                    throw new RoleExistedException();
                });
                role.rename(command.name());
            }
            role.changeStatus(command.status());
            Set<Permission> newPermissions = new HashSet<>(command.permissionCodeList());
            Set<Permission> oldPermissions = role.getPermissionList();

            // revoke
            for (Permission p : new HashSet<>(oldPermissions)) {
                if (!newPermissions.contains(p)) role.revokePermission(p);
            }

            // grant
            for (Permission p : new HashSet<>(newPermissions)) {
                if (!oldPermissions.contains(p)) role.grantPermission(p);
            }
            roleRepository.save(role);
        }
    }
}
