package com.itravel.platform.modules.location.application.service;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.location.api.dto.response.LocationResponse;
import com.itravel.platform.modules.location.api.mapper.LocationRestMapper;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.domain.aggregate.Location;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import com.itravel.platform.modules.location.domain.repository.LocationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationQueryService {
    LocationRepository locationRepository;
    LocationRestMapper mapper;

    public PageResponse<LocationResponse> getLocations(String keyword, Pageable pageable, Boolean isDeleted, LocationStatus status) {
        Page<Location> locationPage = locationRepository.getLocations(keyword, pageable, isDeleted, status);

        return PageResponse.<LocationResponse>builder()
                .currentPage(locationPage.getNumber())
                .pageSize(locationPage.getSize())
                .totalElements(locationPage.getTotalElements())
                .totalPages(locationPage.getTotalPages())
                .data(
                        locationPage.getContent().stream().map(mapper::toLocationResponse).toList()
                )
                .build();
    }

    public List<LocationResponse> getTree(Boolean isDeleted,LocationStatus status) {
        List<Location> locationTree = locationRepository.getTree(isDeleted,status);

        return locationTree.stream().map(mapper::toLocationResponse).toList();
    }

    public LocationResponse getLocation(LocationId id) {
        Location location =  locationRepository.findById(id, true, true, 1)
                .orElseThrow(LocationNotFoundException::new);
        return mapper.toLocationResponse(location);
    }
}
