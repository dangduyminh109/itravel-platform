package com.itravel.platform.modules.identity.application.query.role;

import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.application.port.out.role.RoleQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetRolesHandler {
    RoleQueryPort queryPort;
    public List<RoleDTO> getRoles(String status, String keyword) {
        return queryPort.getRoles(status, keyword);
    }
}
