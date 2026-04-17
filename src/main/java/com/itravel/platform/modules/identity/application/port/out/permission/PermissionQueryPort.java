package com.itravel.platform.modules.identity.application.port.out.permission;

import com.itravel.platform.modules.identity.application.dto.PermissionDTO;
import java.util.List;

public interface PermissionQueryPort {
    List<PermissionDTO> getPermissions();
}
