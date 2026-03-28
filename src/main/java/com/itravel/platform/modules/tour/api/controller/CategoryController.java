package com.itravel.platform.modules.tour.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.tour.api.dto.request.CreateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateStatusCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.response.CategoryResponse;
import com.itravel.platform.modules.tour.api.mapper.CategoryRestMapper;
import com.itravel.platform.modules.tour.application.command.location.CreateCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.UpdateCategoryCommand;
import com.itravel.platform.modules.tour.application.handler.CategoryCommandHandler;
import com.itravel.platform.modules.tour.application.service.CategoryQueryService;
import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/category")
public class CategoryController {
    CategoryCommandHandler categoryCommandHandler;
    CategoryQueryService categoryQueryService;
    CategoryRestMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('CATEGORY_VIEW')")
    public ApiResponse<PageResponse<CategoryResponse>> getCategories(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) CategoryStatus status,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        return ApiResponse.<PageResponse<CategoryResponse>>builder()
                .success(true)
                .response(categoryQueryService.getCategories(keyword, pageable, isDeleted, status))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_VIEW')")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.<CategoryResponse>builder()
                .success(true)
                .response(categoryQueryService.getCategory(new CategoryId(id)))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CATEGORY_CREATE')")
    public ApiResponse<CategoryResponse> create(@RequestBody @Valid CreateCategoryRequest request) {
        CreateCategoryCommand createCategoryCommand = mapper.toCreateCategoryCommand(request);
        return ApiResponse.<CategoryResponse>builder()
                .message("Create category successfully")
                .success(true)
                .response(mapper.toCategoryResponse(categoryCommandHandler.create(createCategoryCommand)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ApiResponse<CategoryResponse> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest
    ) {
        UpdateCategoryCommand updateCategoryCommand = mapper.toUpdateCategoryCommand(id, updateCategoryRequest);
        return ApiResponse.<CategoryResponse>builder()
                .message("Update category successfully")
                .success(true)
                .response(mapper.toCategoryResponse(categoryCommandHandler.update(updateCategoryCommand)))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public ApiResponse<Void> delete(@PathVariable String id) {
        categoryCommandHandler.delete(mapper.toDeleteCategoryCommand(id));
        return ApiResponse.<Void>builder()
                .message("Delete category successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable Long id) {
        categoryCommandHandler.restore(new CategoryId(id));
        return ApiResponse.<Void>builder()
                .message("Restore category successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ApiResponse<Void> status(@PathVariable String id,
                                    @RequestBody @Valid UpdateStatusCategoryRequest request
    ) {
        categoryCommandHandler.status(mapper.toUpdateStatusCategoryCommand(id,request));
        return ApiResponse.<Void>builder()
                .message("Update category status successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        categoryCommandHandler.destroy(mapper.toDeleteCategoryCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy category successfully")
                .success(true)
                .build();
    }
}
