package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.application.port.out.role.RoleQueryPort;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.RoleMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.RoleJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleQueryAdapter implements RoleQueryPort {
    RoleJpaRepository repository;

    @Override
    public List<RoleDTO> getRoles(String status, String keyword) {
        return repository
                .findByStatus(status, keyword)
                .stream()
                .map(RoleMapper::toRoleDTO)
                .toList();
    }
}
