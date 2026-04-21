package com.itravel.platform.modules.location.application.port.out.location;

import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.dto.LocationGeneralInfoDTO;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LocationQueryPort {
    Page<LocationListItemDTO> getLocations(String keyword, Pageable pageable, Boolean isDeleted, LocationStatus status);
    List<LocationDetailDTO> getTree(Boolean isDeleted, LocationStatus status);
    Optional<LocationDetailDTO> findById(Long id, boolean withChildren, boolean withParent, Integer level);
    LocationGeneralInfoDTO getLocationGeneralInfoDTO();
}
