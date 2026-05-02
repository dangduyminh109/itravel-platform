package com.itravel.platform.modules.tour.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.tour.api.dto.request.CreateScheduleRequest;
import com.itravel.platform.modules.tour.api.dto.request.UpdateScheduleRequest;
import com.itravel.platform.modules.tour.api.dto.response.ScheduleDetailResponse;
import com.itravel.platform.modules.tour.api.mapper.ScheduleRestMapper;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.in.schedule.facade.ScheduleCommandFacade;
import com.itravel.platform.modules.tour.application.port.in.schedule.facade.ScheduleQueryFacade;
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
@RequestMapping("/schedule")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleController {
    ScheduleCommandFacade commandFacade;
    ScheduleQueryFacade queryFacade;
    ScheduleRestMapper mapper;

    @GetMapping("/{tourId}")
    @PreAuthorize("hasAuthority('SCHEDULE_VIEW')")
    public ApiResponse<PageResponse<ScheduleDetailResponse>> getTours(
            @PathVariable String tourId,
            @RequestParam(required = false) Boolean isDeleted,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<ScheduleDetailDTO> tourPage = queryFacade.getScheduleList(isDeleted, tourId, pageable);
        PageResponse<ScheduleDetailResponse> response = PageResponse.<ScheduleDetailResponse>builder()
                .currentPage(tourPage.getNumber())
                .pageSize(tourPage.getSize())
                .totalElements(tourPage.getTotalElements())
                .totalPages(tourPage.getTotalPages())
                .data(tourPage.getContent().stream().map(mapper::toScheduleDetailResponse).toList())
                .build();

        return ApiResponse.<PageResponse<ScheduleDetailResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SCHEDULE_CREATE')")
    public ApiResponse<ScheduleDetailResponse> create(@RequestBody @Valid CreateScheduleRequest request) {
        ScheduleDetailDTO dto = commandFacade.create(mapper.toCreateScheduleCommand(request));
        return ApiResponse.<ScheduleDetailResponse>builder()
                .message("Create schedule successfully")
                .success(true)
                .response(mapper.toScheduleDetailResponse(dto))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCHEDULE_UPDATE')")
    public ApiResponse<ScheduleDetailResponse> update(@PathVariable String id, @RequestBody @Valid UpdateScheduleRequest request) {
        var cmd =  mapper.toUpdateScheduleCommand(id, request);
        ScheduleDetailDTO dto = commandFacade.update(cmd);
        return ApiResponse.<ScheduleDetailResponse>builder()
                .message("Update schedule successfully")
                .success(true)
                .response(mapper.toScheduleDetailResponse(dto))
                .build();
    }


    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SCHEDULE_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        commandFacade.destroy(mapper.toDeleteScheduleCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy schedule successfully")
                .success(true)
                .build();
    }
}