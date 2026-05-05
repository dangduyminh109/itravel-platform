package com.itravel.platform.modules.notification.infrastructure.persistence.entity;

import com.itravel.platform.modules.notification.domain.notification.NotificationChannel;
import com.itravel.platform.modules.notification.domain.notification.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Table(name = "notifications")
public class NotificationJpaEntity {
    @Id
    String id;

    @Column(nullable = false)
    String recipientId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    NotificationType type;

    @Column(nullable = false)
    boolean isRead;

    String url;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    Set<NotificationChannel> channels;

    @Column(nullable = false, updatable = false)
    Instant createdAt;
}
