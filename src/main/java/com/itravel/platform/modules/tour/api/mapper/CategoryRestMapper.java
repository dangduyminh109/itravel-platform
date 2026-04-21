package com.itravel.platform.modules.tour.api.mapper;

import com.itravel.platform.modules.tour.api.dto.request.CreateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateStatusCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.response.CategoryGeneralInfoResponse;
import com.itravel.platform.modules.tour.api.dto.response.CategoryResponse;
import com.itravel.platform.modules.tour.application.command.model.category.*;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.dto.CategoryGeneralInfoDTO;
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
    CreateCategoryCommand toCreateCategoryCommand(CreateCategoryRequest request);
    UpdateCategoryCommand toUpdateCategoryCommand(Long id, UpdateCategoryRequest request);
    DeleteCategoryCommand toDeleteCategoryCommand(Long id);
    RestoreCategoryCommand toRestoreCategoryCommand(Long id);
    UpdateStatusCategoryCommand toUpdateStatusCategoryCommand(Long id, UpdateStatusCategoryRequest request);
    CategoryResponse toCategoryResponse(CategoryDetailDTO dto);
    CategoryGeneralInfoResponse toCategoryGeneralInfoResponse(CategoryGeneralInfoDTO dto);
}