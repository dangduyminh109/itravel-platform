package com.itravel.platform.modules.identity.application.port.in.user.facade;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.dto.UserGeneralInfoDTO;
import com.itravel.platform.modules.identity.application.query.user.GetUserGeneralInfoHandler;
import com.itravel.platform.modules.identity.application.query.user.GetUserHandler;
import com.itravel.platform.modules.identity.application.query.user.GetUserMeHandler;
import com.itravel.platform.modules.identity.application.query.user.GetUsersHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserQueryFacade {
    GetUserHandler getUserHandler;
    GetUsersHandler getUsersHandler;
    GetUserGeneralInfoHandler getUserGeneralInfoHandler;
    GetUserMeHandler getUserMeHandler;

    public UserGeneralInfoDTO getUserGeneralInfo() {
        return getUserGeneralInfoHandler.getUserGeneralInfoDTO();
    }

    public PageResponse<UserDetailDTO> getUsers(String keyword, Pageable pageable, boolean isDeleted) {
        return getUsersHandler.getUsers(keyword, pageable, isDeleted);
    }

    public UserDetailDTO getUser(String id) {
        return getUserHandler.getUser(id);
    }

    public UserDetailDTO getMe() {
        return getUserMeHandler.getMe();
    }
}
