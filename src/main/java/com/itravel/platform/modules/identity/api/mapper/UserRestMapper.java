package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.identity.api.dto.request.CreateUserRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateUserRequest;
import com.itravel.platform.modules.identity.api.dto.response.UserGeneralInfoResponse;
import com.itravel.platform.modules.identity.api.dto.response.UserResponse;
import com.itravel.platform.modules.identity.application.command.model.user.*;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.dto.UserGeneralInfoDTO;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                RoleRestMapper.class,
                IdentityValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserRestMapper {
    UserGeneralInfoResponse toUserGeneralInfoResponse(UserGeneralInfoDTO info);
    UserResponse toUserResponse(UserDetailDTO user);
    List<UserResponse> toUserResponseList(List<UserDetailDTO> users);

    default PageResponse<UserResponse> toUserPageResponse(PageResponse<UserDetailDTO> page) {
        if (page == null) return null;
        return PageResponse.<UserResponse>builder()
                .currentPage(page.getCurrentPage())
                .pageSize(page.getPageSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .data(toUserResponseList(page.getData()))
                .build();
    }
    CreateUserCommand toCreateUserCommand(CreateUserRequest request);
    UpdateUserCommand toUpdateUserCommand(String id, UpdateUserRequest request);
    DeleteUserCommand toDeleteUserCommand(String id);
    RestoreUserCommand toRestoreUserCommand(String id);
}
