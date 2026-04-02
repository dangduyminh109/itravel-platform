package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import com.itravel.platform.modules.tour.domain.tourImage.ImageUrl;
import com.itravel.platform.modules.tour.domain.tour.TourImageId;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourImageJpaEntity;
import com.itravel.platform.modules.tour.share.TourImageValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {TourImageValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TourImageMapper {
    TourImageJpaEntity toTourImageJpaEntity(TourImage tourImage);

    static TourImage toTourImageDomain(TourImageJpaEntity entity) {
        return TourImage.fromExistingBuilder()
                .id(new TourImageId(entity.getId()))
                .imageUrl(new ImageUrl(entity.getImageUrl()))
                .isThumbnail(entity.getIsThumbnail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
