package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.role.RoleId;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import com.itravel.platform.modules.identity.application.port.out.role.RoleRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.RoleMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.RoleJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleRepositoryImpl implements RoleRepository {
    RoleJpaRepository roleJpaRepository;
    RoleMapper roleMapper;

    @Override
    public Optional<Role> findById(RoleId roleId) {
        return roleJpaRepository
                .findById(roleId.value())
                .map(RoleMapper::toRoleDomain);
    }

    @Override
    public List<Role> findAllById(List<RoleId> ids) {
        Set<Long> rawIds = ids.stream()
                .map(RoleId::value)
                .collect(Collectors.toSet());
        return roleJpaRepository.findByIdIn(rawIds).stream()
                .map(RoleMapper::toRoleDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> findByRoleName(RoleName rolename) {
        return roleJpaRepository
                .findByName(rolename.value())
                .map(RoleMapper::toRoleDomain);
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity roleJpaEntity = roleMapper.toRoleJpaEntity(role);
        roleJpaEntity.setId(role.getId() == null ? null : role.getId().value());
        RoleJpaEntity saved = roleJpaRepository.save(roleJpaEntity);
        return RoleMapper.toRoleDomain(saved);
    }

    @Override
    public void destroy(RoleId roleId) {
        roleJpaRepository.findById(roleId.value())
                .ifPresent(roleJpaRepository::delete);
    }
}
