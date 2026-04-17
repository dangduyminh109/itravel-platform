package com.itravel.platform.modules.identity.application.query.user;

import com.itravel.platform.modules.identity.application.dto.UserGeneralInfoDTO;
import com.itravel.platform.modules.identity.application.port.out.user.UserQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetUserGeneralInfoHandler {
    UserQueryPort userQueryPort;

    public UserGeneralInfoDTO getUserGeneralInfoDTO() {
        return userQueryPort.getUserGeneralInfoDTO();
    }
}
