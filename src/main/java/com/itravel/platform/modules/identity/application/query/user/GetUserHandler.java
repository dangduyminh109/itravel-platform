package com.itravel.platform.modules.identity.application.query.user;

import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.port.out.user.UserQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetUserHandler {
    UserQueryPort userQueryPort;

    public UserDetailDTO getUser(String id) {
        return userQueryPort.getUser(id)
                .orElseThrow(UserNotExistException::new);
    }
}
