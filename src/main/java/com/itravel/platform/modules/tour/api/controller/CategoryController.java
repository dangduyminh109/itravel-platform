package com.itravel.platform.modules.tour.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.tour.api.dto.request.CreateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateStatusCategoryRequest;
import com.itravel.platform.modules.tour.api.dto.response.CategoryResponse;
import com.itravel.platform.modules.tour.api.mapper.CategoryRestMapper;
import com.itravel.platform.modules.tour.application.command.model.category.CreateCategoryCommand;
import com.itravel.platform.modules.tour.application.command.model.category.UpdateCategoryCommand;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.port.in.category.facade.CategoryCommandFacade;
import com.itravel.platform.modules.tour.application.port.in.category.facade.CategoryQueryFacade;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
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
    CategoryRestMapper mapper;
    CategoryQueryFacade queryFacade;
    CategoryCommandFacade commandFacade;

    @GetMapping
    @PreAuthorize("hasAuthority('CATEGORY_VIEW')")
    public ApiResponse<PageResponse<CategoryResponse>> getCategories(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) String status,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        Page<CategoryDetailDTO> categoryPage = queryFacade.getAll(keyword, pageable, isDeleted, status);
        PageResponse<CategoryResponse> response = PageResponse.<CategoryResponse>builder()
                .currentPage(categoryPage.getNumber())
                .pageSize(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .data(categoryPage.getContent().stream().map(mapper::toCategoryResponse).toList())
                .build();

        return ApiResponse.<PageResponse<CategoryResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_VIEW')")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.<CategoryResponse>builder()
                .success(true)
                .response(mapper.toCategoryResponse(queryFacade.getById(id)))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CATEGORY_CREATE')")
    public ApiResponse<Void> create(@RequestBody @Valid CreateCategoryRequest request) {
        CreateCategoryCommand createCategoryCommand = mapper.toCreateCategoryCommand(request);
        commandFacade.create(createCategoryCommand);
        return ApiResponse.<Void>builder()
                .message("Create category successfully")
                .success(true)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ApiResponse<Void> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest
    ) {
        UpdateCategoryCommand updateCategoryCommand = mapper.toUpdateCategoryCommand(id, updateCategoryRequest);
        commandFacade.update(updateCategoryCommand);
        return ApiResponse.<Void>builder()
                .message("Update category successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        commandFacade.delete(mapper.toDeleteCategoryCommand(id));
        return ApiResponse.<Void>builder()
                .message("Delete category successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable Long id) {
        commandFacade.restore(mapper.toRestoreCategoryCommand(id));
        return ApiResponse.<Void>builder()
                .message("Restore category successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
    public ApiResponse<Void> status(@PathVariable Long id,
                                    @RequestBody @Valid UpdateStatusCategoryRequest request
    ) {
        commandFacade.updateStatus(mapper.toUpdateStatusCategoryCommand(id,request));
        return ApiResponse.<Void>builder()
                .message("Update category status successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable Long id) {
        commandFacade.destroy(mapper.toDeleteCategoryCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy category successfully")
                .success(true)
                .build();
    }
}
