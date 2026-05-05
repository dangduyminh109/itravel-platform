package com.itravel.platform.modules.notification.api.mapper;

import com.itravel.platform.modules.notification.api.dto.response.NotificationResponse;
import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface NotificationRestMapper {
    @Mapping(target = "isRead", source = "read")
    NotificationResponse toResponse(NotificationDTO dto);
}
