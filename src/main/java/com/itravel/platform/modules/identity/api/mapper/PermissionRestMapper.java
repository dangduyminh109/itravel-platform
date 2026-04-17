package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.response.PermissionResponse;
import com.itravel.platform.modules.identity.application.dto.PermissionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionRestMapper {
    PermissionResponse toPermissionResponse(PermissionDTO dto);
    List<PermissionResponse> toPermissionResponseList(List<PermissionDTO> dtos);
}
