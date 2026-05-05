package com.itravel.platform.modules.notification.domain.notification;

import com.itravel.platform.modules.notification.domain.event.NotificationCreatedEvent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    final NotificationId id;
    final String recipientId;
    final String title;
    final String content;
    final NotificationType type;
    boolean isRead;
    final String url;
    final Set<NotificationChannel> channels;
    final Instant createdAt;

    @Getter(AccessLevel.NONE)
    final transient List<Object> domainEvents = new ArrayList<>();

    private Notification(
            String recipientId,
            String title,
            String content,
            NotificationType type,
            String url,
            Set<NotificationChannel> channels
    ) {
        this.id = NotificationId.generate();
        this.recipientId = recipientId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.url = url;
        this.isRead = false;
        this.channels = channels != null ? new HashSet<>(channels) : new HashSet<>();
        this.createdAt = Instant.now();
    }

    private Notification(
            NotificationId id,
            String recipientId,
            String title,
            String content,
            NotificationType type,
            boolean isRead,
            String url,
            Set<NotificationChannel> channels,
            Instant createdAt
    ) {
        this.id = id;
        this.recipientId = recipientId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
        this.url = url;
        this.channels = channels != null ? new HashSet<>(channels) : new HashSet<>();
        this.createdAt = createdAt;
    }

    public static Notification create(
            String recipientId,
            String title,
            String content,
            NotificationType type,
            String url,
            Set<NotificationChannel> channels
    ) {
        Notification notification = new Notification(recipientId, title, content, type, url, channels);
        
        notification.registerEvent(new NotificationCreatedEvent(
                notification.getId().value(),
                notification.getRecipientId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getType(),
                notification.getChannels(),
                notification.getCreatedAt()
        ));
        
        return notification;
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Notification fromExisting(
            NotificationId id,
            String recipientId,
            String title,
            String content,
            NotificationType type,
            boolean isRead,
            String url,
            Set<NotificationChannel> channels,
            Instant createdAt
    ) {
        return new Notification(
                id, recipientId, title, content, type, isRead, url, channels,
                createdAt
        );
    }

    public Set<NotificationChannel> getChannels() {
        return Collections.unmodifiableSet(channels);
    }

    protected void registerEvent(Object event) {
        this.domainEvents.add(event);
    }

    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public void markAsUnread() {
        this.isRead = false;
    }
}
