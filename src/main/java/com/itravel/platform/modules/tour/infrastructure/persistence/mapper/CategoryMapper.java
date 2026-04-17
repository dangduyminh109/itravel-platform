package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.domain.category.Category;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
import com.itravel.platform.modules.tour.domain.category.CategoryName;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import com.itravel.platform.modules.tour.share.CategoryValueObjectMapper;
import com.itravel.platform.modules.tour.share.CommonValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {CategoryValueObjectMapper.class, CommonValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {
    CategoryJpaEntity toCategoryJpaEntity(Category category);

    static Category toCategoryDomain(CategoryJpaEntity entity) {
        return Category.fromExistingBuilder()
                .id(new CategoryId(entity.getId()))
                .name(new CategoryName(entity.getName()))
                .slug(new Slug(entity.getSlug()))
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    static CategoryDetailDTO toCategoryDetailDTO(CategoryJpaEntity entity) {
        return CategoryDetailDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .description(entity.getDescription())
                .status(entity.getStatus().toString())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
