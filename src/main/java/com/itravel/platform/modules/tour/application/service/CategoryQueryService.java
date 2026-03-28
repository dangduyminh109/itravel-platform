package com.itravel.platform.modules.tour.application.service;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.tour.api.dto.response.CategoryResponse;
import com.itravel.platform.modules.tour.api.mapper.CategoryRestMapper;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.domain.aggregate.Category;
import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryQueryService {
    CategoryRepository categoryRepository;
    CategoryRestMapper mapper;

    public PageResponse<CategoryResponse> getCategories(String keyword, Pageable pageable, Boolean isDeleted, CategoryStatus status) {
        Page<Category> categoryPage = categoryRepository.getCategories(keyword, pageable, isDeleted, status);

        return PageResponse.<CategoryResponse>builder()
                .currentPage(categoryPage.getNumber())
                .pageSize(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .data(categoryPage.getContent().stream().map(mapper::toCategoryResponse).toList())
                .build();
    }

    public CategoryResponse getCategory(CategoryId id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        return mapper.toCategoryResponse(category);
    }
}
