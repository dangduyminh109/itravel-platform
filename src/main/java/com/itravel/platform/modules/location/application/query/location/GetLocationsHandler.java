package com.itravel.platform.modules.location.application.query.location;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.application.port.out.location.LocationQueryPort;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetLocationsHandler {
    LocationQueryPort locationQueryPort;

    public PageResponse<LocationListItemDTO> execute(String keyword, Pageable pageable, Boolean isDeleted, LocationStatus status) {
        Page<LocationListItemDTO> locationPage = locationQueryPort.getLocations(keyword, pageable, isDeleted, status);

        return PageResponse.<LocationListItemDTO>builder()
                .currentPage(locationPage.getNumber())
                .pageSize(locationPage.getSize())
                .totalElements(locationPage.getTotalElements())
                .totalPages(locationPage.getTotalPages())
                .data(locationPage.getContent())
                .build();
    }
}
