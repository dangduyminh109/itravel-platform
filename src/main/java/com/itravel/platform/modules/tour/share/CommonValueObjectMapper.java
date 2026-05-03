package com.itravel.platform.modules.tour.share;

import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CommonValueObjectMapper {
    default Slug toSlug(String slug) {
        return slug != null ? new Slug(slug) : null;
    }

    default String fromSlug(Slug slug) {
        return slug != null ? slug.value() : null;
    }
}