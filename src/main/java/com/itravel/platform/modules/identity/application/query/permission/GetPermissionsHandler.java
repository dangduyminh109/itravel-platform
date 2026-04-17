package com.itravel.platform.modules.identity.application.query.permission;

import com.itravel.platform.modules.identity.application.dto.PermissionDTO;
import com.itravel.platform.modules.identity.application.port.out.permission.PermissionQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetPermissionsHandler {
    PermissionQueryPort queryPort;
    public List<PermissionDTO> getPermissions() {
        return queryPort.getPermissions();
    }
}
