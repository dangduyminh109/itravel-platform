package com.itravel.platform.modules.tour.share;

import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryName;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryValueObjectMapper {
    default CategoryId toCategoryId(Long id) {
        return id != null ? new CategoryId(id) : null;
    }

    default Long fromCategoryId(CategoryId id) {
        return id != null ? id.value() : null;
    }

    default CategoryName toCategoryName(String name) {
        return name != null ? new CategoryName(name) : null;
    }

    default String fromCategoryName(CategoryName name) {
        return name != null ? name.value() : null;
    }

    default Slug toSlug(String slug) {
        return slug != null ? new Slug(slug) : null;
    }

    default String fromSlug(Slug slug) {
        return slug != null ? slug.value() : null;
    }
}