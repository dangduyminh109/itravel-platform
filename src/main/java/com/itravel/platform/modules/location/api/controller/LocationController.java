package com.itravel.platform.modules.location.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.location.api.dto.request.CreateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateStatusLocationRequest;
import com.itravel.platform.modules.location.api.dto.response.LocationResponse;
import com.itravel.platform.modules.location.api.mapper.LocationRestMapper;
import com.itravel.platform.modules.location.application.command.location.CreateLocationCommand;
import com.itravel.platform.modules.location.application.command.location.UpdateLocationCommand;
import com.itravel.platform.modules.location.application.handler.LocationCommandHandler;
import com.itravel.platform.modules.location.application.service.LocationQueryService;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/location")
public class LocationController {
    LocationCommandHandler locationCommandHandler;
    LocationQueryService locationQueryService;
    LocationRestMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('LOCATION_VIEW')")
    public ApiResponse<PageResponse<LocationResponse>> getLocations(
            @RequestParam (required = false) String keyword,
            @RequestParam (required = false) Boolean isDeleted,
            @RequestParam (required = false) LocationStatus status,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        return ApiResponse.<PageResponse<LocationResponse>>builder()
                .success(true)
                .response(locationQueryService.getLocations(keyword, pageable, isDeleted,status))
                .build();
    }

    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('LOCATION_VIEW')")
    public ApiResponse<List<LocationResponse>> getTree(
            @RequestParam (required = false,defaultValue = "false") Boolean isDeleted,
            @RequestParam (required = false) LocationStatus status
    ) {
        return ApiResponse.<List<LocationResponse>>builder()
                .success(true)
                .response(locationQueryService.getTree(isDeleted,status))
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATION_VIEW')")
    public ApiResponse<LocationResponse> getLocation(
            @PathVariable Long id
    ) {
        return ApiResponse.<LocationResponse>builder()
                .success(true)
                .response(locationQueryService.getLocation(new LocationId(id)))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('LOCATION_CREATE')")
    public ApiResponse<LocationResponse> create(@RequestBody @Valid CreateLocationRequest request) {
        CreateLocationCommand createLocationCommand = mapper.toCreateLocationCommand(request);
        return ApiResponse.<LocationResponse>builder()
                .message("Create location successfully")
                .success(true)
                .response(mapper.toLocationResponse(locationCommandHandler.create(createLocationCommand)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATION_UPDATE')")
    public ApiResponse<LocationResponse> update(@PathVariable String id, @RequestBody @Valid UpdateLocationRequest updateLocationRequest) {
        UpdateLocationCommand updateLocationCommand = mapper.toUpdateLocationCommand(id, updateLocationRequest);
        return ApiResponse.<LocationResponse>builder()
                .message("Update location successfully")
                .success(true)
                .response(mapper.toLocationResponse(locationCommandHandler.update(updateLocationCommand)))
                .build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LOCATION_DELETE')")
    public ApiResponse<Void> delete(@PathVariable String id) {
        locationCommandHandler.delete(mapper.toDeleteLocationCommand(id));
        return ApiResponse.<Void>builder()
                .message("Delete location successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('LOCATION_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable String id) {
        locationCommandHandler.restore(mapper.toRestoreLocationCommand(id));
        return ApiResponse.<Void>builder()
                .message("Restore location successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAuthority('LOCATION_UPDATE')")
    public ApiResponse<Void> status(
            @PathVariable String id,
            @RequestBody @Valid UpdateStatusLocationRequest request) {
        locationCommandHandler.status(mapper.toUpdateStatusLocationCommand(id,request));
        return ApiResponse.<Void>builder()
                .message("Update status location successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LOCATION_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        locationCommandHandler.destroy(mapper.toDeleteLocationCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy location successfully")
                .success(true)
                .build();
    }
}
