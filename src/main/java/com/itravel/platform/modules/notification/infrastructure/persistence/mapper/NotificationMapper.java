package com.itravel.platform.modules.notification.infrastructure.persistence.mapper;

import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import com.itravel.platform.modules.notification.domain.notification.Notification;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;
import com.itravel.platform.modules.notification.infrastructure.persistence.entity.NotificationJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface NotificationMapper {
    @Mapping(target = "id", expression = "java(notification.getId().value())")
    @Mapping(target = "isRead", source = "read")
    NotificationJpaEntity toJpaEntity(Notification notification);

    static Notification toDomain(NotificationJpaEntity entity) {
        if (entity == null) return null;
        return Notification.fromExistingBuilder()
                .id(new NotificationId(entity.getId()))
                .recipientId(entity.getRecipientId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .type(entity.getType())
                .isRead(entity.isRead())
                .url(entity.getUrl())
                .channels(entity.getChannels())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    static NotificationDTO toDTO(NotificationJpaEntity entity) {
        if (entity == null) return null;
        return NotificationDTO.builder()
                .id(entity.getId())
                .recipientId(entity.getRecipientId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .type(entity.getType())
                .isRead(entity.isRead())
                .url(entity.getUrl())
                .channels(entity.getChannels())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
