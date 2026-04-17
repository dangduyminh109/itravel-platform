package com.itravel.platform.modules.identity.application.port.out.user;

import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.dto.UserGeneralInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserQueryPort {
    UserGeneralInfoDTO getUserGeneralInfoDTO();
    Page<UserDetailDTO> getUsers(String keyword, Pageable pageable, boolean isDeleted);
    Optional<UserDetailDTO> getUser(String id);
}
