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
    Instant deletedAt;

    protected BaseAggregate(ID id) {
        this.id = id;
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.deletedAt = null;
    }

    protected BaseAggregate(ID id, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    protected void touch() {
        this.updatedAt = Instant.now();
    }

    public void softDelete() {
        if (deletedAt != null) {
            return;
        }
        this.deletedAt = Instant.now();
        touch();
    }

    public void restore() {
        if (deletedAt == null) {
            return;
        }
        this.deletedAt = null;
        touch();
    }
}
