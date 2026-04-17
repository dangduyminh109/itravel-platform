package com.itravel.platform.modules.location.application.query.location;

import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.application.port.out.location.LocationQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetLocationHandler {
    LocationQueryPort locationQueryPort;

    public LocationDetailDTO execute(Long id) {
        return locationQueryPort.findById(id, true, true, 1)
                .orElseThrow(LocationNotFoundException::new);
    }
}
