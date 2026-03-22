package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import com.itravel.platform.modules.identity.domain.repository.RoleRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.RoleMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
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
    public List<Role> findAllById(List<RoleId> roleIds) {
        return roleJpaRepository
                .findByIdIn(roleIds.stream().map(RoleId::value).collect(Collectors.toSet()))
                .stream().map(RoleMapper::toRoleDomain).toList();
    }

    @Override
    public Optional<Role> findByRoleName(RoleName rolename) {
        return roleJpaRepository
                .findByName(rolename.value())
                .map(RoleMapper::toRoleDomain);
    }

    @Override
    public List<Role> getRoles(RoleStatus status, String keyword) {
        return roleJpaRepository
                .findByStatus(status == null ? null: status.name(),keyword)
                .stream()
                .map(RoleMapper::toRoleDomain)
                .toList();
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
