package com.itravel.platform.modules.location.application.query.location;

import com.itravel.platform.modules.location.application.dto.LocationGeneralInfoDTO;
import com.itravel.platform.modules.location.application.port.out.location.LocationQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetLocationGeneralInfoHandler {
    LocationQueryPort locationQueryPort;

    public LocationGeneralInfoDTO execute() {
        return locationQueryPort.getLocationGeneralInfoDTO();
    }
}
