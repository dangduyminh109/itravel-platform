package com.itravel.platform.modules.location.application.query.location;

import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.port.out.location.LocationQueryPort;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetLocationTreeHandler {
    LocationQueryPort locationQueryPort;

    public List<LocationDetailDTO> execute(Boolean isDeleted, LocationStatus status) {
        return locationQueryPort.getTree(isDeleted, status);
    }
}
