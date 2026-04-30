package com.itravel.platform.modules.tour.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.tour.api.dto.request.CreateTourRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateTourRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateTourStatusRequest;
import com.itravel.platform.modules.tour.api.dto.response.TourDetailResponse;
import com.itravel.platform.modules.tour.api.dto.response.TourListItemResponse;
import com.itravel.platform.modules.tour.api.dto.response.TourResponse;
import com.itravel.platform.modules.tour.api.mapper.TourRestMapper;
import com.itravel.platform.modules.tour.application.command.model.tour.UpdateTourCommand;
import com.itravel.platform.modules.tour.application.dto.TourDTO;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.dto.TourListItemDTO;
import com.itravel.platform.modules.tour.application.port.in.tour.facade.TourCommandFacade;
import com.itravel.platform.modules.tour.application.port.in.tour.facade.TourQueryFacade;
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
@RequestMapping("/tour")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourController {
    TourCommandFacade commandFacade;
    TourQueryFacade queryFacade;
    TourRestMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('TOUR_VIEW')")
    public ApiResponse<PageResponse<TourListItemResponse>> getTours(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long departureId,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<TourListItemDTO> tourPage = queryFacade.getTours(keyword, isDeleted, status, categoryId, departureId, pageable);
        PageResponse<TourListItemResponse> response = PageResponse.<TourListItemResponse>builder()
                .currentPage(tourPage.getNumber())
                .pageSize(tourPage.getSize())
                .totalElements(tourPage.getTotalElements())
                .totalPages(tourPage.getTotalPages())
                .data(tourPage.getContent().stream().map(mapper::toTourListItemResponse).toList())
                .build();

        return ApiResponse.<PageResponse<TourListItemResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TOUR_VIEW')")
    public ApiResponse<TourResponse> getTour(@PathVariable String id) {
        TourDTO dto = queryFacade.getTour(id);
        return ApiResponse.<TourResponse>builder()
                .success(true)
                .response(mapper.toTourResponse(dto))
                .build();
    }

    @GetMapping("/{slugOrId}/detail")
    @PreAuthorize("hasAuthority('TOUR_VIEW')")
    public ApiResponse<TourDetailResponse> getTourDetail(@PathVariable String slugOrId) {
        TourDetailDTO dto = queryFacade.getDetail(slugOrId);
        return ApiResponse.<TourDetailResponse>builder()
                .success(true)
                .response(mapper.toTourDetailResponse(dto))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('TOUR_CREATE')")
    public ApiResponse<TourDetailResponse> create(@ModelAttribute @Valid CreateTourRequest request) {
        TourDetailDTO dto = commandFacade.create(mapper.toCreateTourCommand(request));
        return ApiResponse.<TourDetailResponse>builder()
                .message("Create tour successfully")
                .success(true)
                .response(mapper.toTourDetailResponse(dto))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TOUR_UPDATE')")
    public ApiResponse<TourDetailResponse> update(@PathVariable String id, @ModelAttribute @Valid UpdateTourRequest request) {
        UpdateTourCommand cmd =  mapper.toUpdateTourCommand(id, request);
        TourDetailDTO dto = commandFacade.update(cmd);
        return ApiResponse.<TourDetailResponse>builder()
                .message("Update tour successfully")
                .success(true)
                .response(mapper.toTourDetailResponse(dto))
                .build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAuthority('TOUR_UPDATE')")
    public ApiResponse<Void> updateStatus(@PathVariable String id, @RequestBody @Valid UpdateTourStatusRequest request) {
        commandFacade.updateStatus(mapper.toUpdateTourStatusCommand(id, request));
        return ApiResponse.<Void>builder()
                .message("Update tour status successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('TOUR_DELETE')")
    public ApiResponse<Void> delete(@PathVariable String id) {
        commandFacade.delete(mapper.toDeleteTourCommand(id));
        return ApiResponse.<Void>builder()
                .message("Delete tour successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('TOUR_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        commandFacade.destroy(mapper.toDeleteTourCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy tour successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('TOUR_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable String id) {
        commandFacade.restore(mapper.toRestoreTourCommand(id));
        return ApiResponse.<Void>builder()
                .message("Restore tour successfully")
                .success(true)
                .build();
    }
}