package com.itravel.platform.modules.identity.application.port.out.role;

import com.itravel.platform.modules.identity.application.dto.RoleDTO;

import java.util.List;

public interface RoleQueryPort {
    List<RoleDTO> getRoles(String status, String keyword);
}
