package com.itravel.platform.modules.identity.application.port.in.permission.facade;

import com.itravel.platform.modules.identity.application.dto.PermissionDTO;
import com.itravel.platform.modules.identity.application.query.permission.GetPermissionsHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionQueryFacade {
    GetPermissionsHandler getPermissionsHandler;

    public List<PermissionDTO> getPermissions() {
        return getPermissionsHandler.getPermissions();
    }
}

