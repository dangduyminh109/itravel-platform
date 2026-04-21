package com.itravel.platform.modules.location.application.port.in.location.facade;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.dto.LocationGeneralInfoDTO;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.application.query.location.*;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationQueryFacade {
    GetLocationsHandler getLocationsHandler;
    GetLocationHandler getLocationHandler;
    GetLocationTreeHandler getLocationTreeHandler;
    GetLocationGeneralInfoHandler getLocationGeneralInfoHandler;

    public PageResponse<LocationListItemDTO> getLocations(String keyword, Pageable pageable, Boolean isDeleted, LocationStatus status) {
        return getLocationsHandler.execute(keyword, pageable, isDeleted, status);
    }

    public List<LocationDetailDTO> getTree(Boolean isDeleted, LocationStatus status) {
        return getLocationTreeHandler.execute(isDeleted, status);
    }

    public LocationDetailDTO getLocation(Long id) {
        return getLocationHandler.execute(id);
    }

    public LocationGeneralInfoDTO getLocationGeneralInfo() {
        return getLocationGeneralInfoHandler.execute();
    }
}
