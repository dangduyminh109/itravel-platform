package com.itravel.platform.modules.tour.domain.aggregate;

import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ImageUrl;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.TourImageId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourImage extends BaseAggregate<TourImageId> {
    TourImageId id;
    ImageUrl imageUrl;
    Boolean isThumbnail;

    private TourImage(
            TourImageId id,
            ImageUrl imageUrl,
            Boolean isThumbnail
    ) {
        super(id);
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
    }

    private TourImage(
            TourImageId id,
            ImageUrl imageUrl,
            Boolean isThumbnail,
            Instant createdAt,
            Instant updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.id = id;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
    }

    public static TourImage create(
            ImageUrl imageUrl,
            Boolean isThumbnail
    ) {
        return new TourImage(
                null,
                imageUrl,
                isThumbnail
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static TourImage fromExisting(
            TourImageId id,
            ImageUrl imageUrl,
            Boolean isThumbnail,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new TourImage(
                id,
                imageUrl,
                isThumbnail,
                createdAt,
                updatedAt
        );
    }

    public void updateImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
        touch();
    }

    public void updateIsThumbnail(Boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
        touch();
    }
}
