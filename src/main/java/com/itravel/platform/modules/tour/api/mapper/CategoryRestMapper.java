package com.itravel.platform.modules.tour.api.mapper;

import com.itravel.platform.modules.tour.api.dto.request.CreateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateStatusCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.response.CategoryResponse;
import com.itravel.platform.modules.tour.application.command.location.CreateCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.DeleteCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.UpdateCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.UpdateStatusCategoryCommand;
import com.itravel.platform.modules.tour.domain.aggregate.Category;
import com.itravel.platform.modules.tour.share.CategoryValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryRestMapper {
    CategoryResponse toCategoryResponse(Category category);
    CreateCategoryCommand toCreateCategoryCommand(CreateCategoryRequest request);
    UpdateCategoryCommand toUpdateCategoryCommand(String id, UpdateCategoryRequest request);
    DeleteCategoryCommand toDeleteCategoryCommand(String id);
    UpdateStatusCategoryCommand toUpdateStatusCategoryCommand(String id, UpdateStatusCategoryRequest request);
}