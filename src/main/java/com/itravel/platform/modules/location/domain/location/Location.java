package com.itravel.platform.modules.location.domain.location;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.location.domain.location.exception.InvalidTypeOrParentException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location extends SoftDeletableAggregate<LocationId> {
    LocationName name;
    Slug slug;
    LocationType type;
    Location parent;
    List<Location> children;
    LocationStatus status;

    private Location(
            LocationId id,
            LocationName name,
            LocationType type,
            Location parent,
            LocationStatus status
    ) {
        super(id);
        this.name = name;
        this.type = type;
        this.status = status;
        this.slug = Slug.toSlug(name.value());
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    private Location(
            LocationId id,
            LocationName name,
            LocationType type,
            Location parent,
            List<Location> children,
            Slug slug,
            LocationStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.name = name;
        this.type = type;
        this.status = status;
        this.slug = slug;
        this.parent = parent;
        this.children = children;
    }

    public static Location create(
            LocationName name,
            LocationType type,
            Location parent,
            LocationStatus status
    ) {
        checkType(parent, type);
        return new Location(
                null,
                name,
                type,
                parent,
                status
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Location fromExisting (
            LocationId id,
            LocationName name,
            LocationType type,
            Location parent,
            List<Location> children,
            Slug slug,
            LocationStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Location(
                id,
                name,
                type,
                parent,
                children,
                slug,
                status,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    private static void checkType(Location parent, LocationType type) {
        if (parent != null && type.ordinal() <= parent.getType().ordinal()) {
            throw new InvalidTypeOrParentException();
        }
    }

    public void updateName(LocationName name) {
        this.name = name;
        touch();
    }
    public void updateStatus(LocationStatus status) {
        this.status = status;
        touch();
    }
    public void updateType(LocationType type) {
        checkType(parent, type);
        this.type = type;
        touch();
    }

    public void updateParent(Location newParent) {
        checkType(newParent, this.type);
        this.parent = newParent;
        touch();
    }

    public void setParentForRead(Location newParent) {
        this.parent = newParent;
    }
}
