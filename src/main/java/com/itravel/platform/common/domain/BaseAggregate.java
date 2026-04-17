package com.itravel.platform.common.domain;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseAggregate<ID> {
    final ID id;
    Instant createdAt;
    Instant updatedAt;

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
}
