package com.itravel.platform.modules.identity.application.query.user;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.port.out.user.UserQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetUsersHandler {
    UserQueryPort userQueryPort;

    public PageResponse<UserDetailDTO> getUsers(String keyword, Pageable pageable, boolean isDeleted) {
        Page<UserDetailDTO> responsePage = userQueryPort.getUsers(keyword, pageable, isDeleted);

        return PageResponse.<UserDetailDTO>builder()
                .currentPage(responsePage.getNumber())
                .pageSize(responsePage.getSize())
                .totalPages(responsePage.getTotalPages())
                .totalElements(responsePage.getTotalElements())
                .data(responsePage.getContent())
                .build();
    }
}
