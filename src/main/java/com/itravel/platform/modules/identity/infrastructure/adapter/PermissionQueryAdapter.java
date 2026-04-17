package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.application.dto.PermissionDTO;
import com.itravel.platform.modules.identity.application.port.out.permission.PermissionQueryPort;
import com.itravel.platform.modules.identity.domain.role.PermissionCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionQueryAdapter implements PermissionQueryPort {

    @Override
    public List<PermissionDTO> getPermissions() {
        return Arrays.stream(PermissionCode.values())
                .map(entity -> PermissionDTO.builder()
                        .code(entity.getCode())
                        .description(entity.getDescription())
                        .group(entity.getGroup())
                        .build())
                .toList();
    }
}
