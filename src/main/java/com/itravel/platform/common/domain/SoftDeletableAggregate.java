package com.itravel.platform.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class SoftDeletableAggregate<ID> extends BaseAggregate<ID> {
    Instant deletedAt;

    protected SoftDeletableAggregate(ID id) {
        super(id);
        this.deletedAt = null;
    }

    protected SoftDeletableAggregate(ID id, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        super(id, createdAt, updatedAt);
        this.deletedAt = deletedAt;
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

