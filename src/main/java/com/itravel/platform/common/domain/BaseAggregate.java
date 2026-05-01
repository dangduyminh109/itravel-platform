package com.itravel.platform.common.domain;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseAggregate<ID> {
    final ID id;
    Instant createdAt;
    Instant updatedAt;

    @Getter(AccessLevel.NONE)
    private final transient List<Object> domainEvents = new ArrayList<>();

    protected BaseAggregate(ID id) {
        this.id = id;
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    protected BaseAggregate(ID id, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void touch() {
        this.updatedAt = Instant.now();
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
}
