package com.itravel.platform.modules.identity.application.port.in.role.facade;

import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.application.query.role.GetRolesHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleQueryFacade {
    GetRolesHandler getRolesHandler;

    public List<RoleDTO> getRoles(String status, String keyword) {
        return getRolesHandler.getRoles(status, keyword);
    }
}
