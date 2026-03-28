package com.itravel.platform.modules.tour.domain.aggregate;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends SoftDeletableAggregate<CategoryId> {
    CategoryId id;
    CategoryName name;
    Slug slug;
    String description;
    CategoryStatus status;

    private Category(
            CategoryName name,
            String description,
            CategoryStatus status
    ) {
        super(null);
        this.name = name;
        this.description = description;
        this.status = status;
        this.slug = Slug.toSlug(name.value());
    }

    private Category(
            CategoryId id,
            CategoryName name,
            String description,
            Slug slug,
            CategoryStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.slug = slug;
    }

    public static Category create(
            CategoryName name,
            String description,
            CategoryStatus status
    ) {
        return new Category(
                name,
                description,
                status
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Category fromExisting(
            CategoryId id,
            CategoryName name,
            String description,
            Slug slug,
            CategoryStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Category(
                id,
                name,
                description,
                slug,
                status,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void updateName(CategoryName name) {
        this.name = name;
        this.slug = Slug.toSlug(name.value());
        touch();
    }

    public void updateStatus(CategoryStatus status) {
        this.status = status;
        touch();
    }

    public void updateDescription(String description) {
        this.description = description;
        touch();
    }
}
