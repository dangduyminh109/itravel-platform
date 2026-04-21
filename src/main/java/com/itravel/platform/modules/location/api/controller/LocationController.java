package com.itravel.platform.modules.location.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.location.api.dto.request.CreateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateLocationRequest;
import com.itravel.platform.modules.location.api.dto.request.UpdateStatusLocationRequest;
import com.itravel.platform.modules.location.api.dto.response.LocationGeneralInfoResponse;
import com.itravel.platform.modules.location.api.dto.response.LocationResponse;
import com.itravel.platform.modules.location.api.mapper.LocationRestMapper;
import com.itravel.platform.modules.location.application.command.location.CreateLocationCommand;
import com.itravel.platform.modules.location.application.command.location.UpdateLocationCommand;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.application.port.in.location.facade.LocationCommandFacade;
import com.itravel.platform.modules.location.application.port.in.location.facade.LocationQueryFacade;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
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
    LocationCommandFacade locationCommandFacade;
    LocationQueryFacade locationQueryFacade;
    LocationRestMapper mapper;

    @GetMapping("/general-info")
    @PreAuthorize("hasAuthority('LOCATION_VIEW')")
    public ApiResponse<LocationGeneralInfoResponse> getGeneralInfo() {
        return ApiResponse.<LocationGeneralInfoResponse>builder()
                .success(true)
                .response(mapper.toLocationGeneralInfoResponse(locationQueryFacade.getLocationGeneralInfo()))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LOCATION_VIEW')")
    public ApiResponse<PageResponse<LocationListItemDTO>> getLocations(
            @RequestParam (required = false) String keyword,
            @RequestParam (required = false) Boolean isDeleted,
            @RequestParam (required = false) LocationStatus status,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        var pageDTO = locationQueryFacade.getLocations(keyword, pageable, isDeleted, status);
        return ApiResponse.<PageResponse<LocationListItemDTO>>builder()
                .success(true)
                .response(mapper.toPageResponse(pageDTO))
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
                .response(locationQueryFacade.getTree(isDeleted,status).stream()
                        .map(mapper::toLocationResponse).toList())
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATION_VIEW')")
    public ApiResponse<LocationResponse> getLocation(
            @PathVariable Long id
    ) {
        return ApiResponse.<LocationResponse>builder()
                .success(true)
                .response(mapper.toLocationResponse(locationQueryFacade.getLocation(id)))
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
                .response(mapper.toLocationResponse(locationCommandFacade.create(createLocationCommand)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATION_UPDATE')")
    public ApiResponse<LocationResponse> update(@PathVariable String id, @RequestBody @Valid UpdateLocationRequest updateLocationRequest) {
        UpdateLocationCommand updateLocationCommand = mapper.toUpdateLocationCommand(id, updateLocationRequest);
        return ApiResponse.<LocationResponse>builder()
                .message("Update location successfully")
                .success(true)
                .response(mapper.toLocationResponse(locationCommandFacade.update(updateLocationCommand)))
                .build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LOCATION_DELETE')")
    public ApiResponse<Void> delete(@PathVariable String id) {
        locationCommandFacade.delete(mapper.toDeleteLocationCommand(id));
        return ApiResponse.<Void>builder()
                .message("Delete location successfully")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("hasAuthority('LOCATION_UPDATE')")
    public ApiResponse<Void> restore(@PathVariable String id) {
        locationCommandFacade.restore(mapper.toRestoreLocationCommand(id));
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
        locationCommandFacade.status(mapper.toUpdateStatusLocationCommand(id,request));
        return ApiResponse.<Void>builder()
                .message("Update status location successfully")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('LOCATION_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable String id) {
        locationCommandFacade.destroy(mapper.toDeleteLocationCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy location successfully")
                .success(true)
                .build();
    }
}
