package com.itravel.platform.modules.tour.share;

import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ImageUrl;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.TourImageId;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TourImageValueObjectMapper {
    default TourImageId toTourImageId(Long id) {
        return id != null ? new TourImageId(id) : null;
    }

    default Long fromTourImageId(TourImageId id) {
        return id != null ? id.value() : null;
    }

    default ImageUrl toImageUrl(String url) {
        return url != null ? new ImageUrl(url) : null;
    }

    default String fromImageUrl(ImageUrl url) {
        return url != null ? url.value() : null;
    }
}

