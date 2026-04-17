package com.itravel.platform.modules.location.application.command.service.location;

import com.itravel.platform.modules.location.application.command.location.RestoreLocationCommand;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.application.port.in.location.RestoreLocationUseCase;
import com.itravel.platform.modules.location.application.port.out.location.LocationRepository;
import com.itravel.platform.modules.location.domain.location.Location;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestoreLocationService implements RestoreLocationUseCase {
    LocationRepository locationRepository;

    @Override
    @Transactional
    public void execute(RestoreLocationCommand command) {
        Location location = locationRepository.findById(command.id(), false, false, 0)
                .orElseThrow(LocationNotFoundException::new);
        location.restore();
        locationRepository.restore(location);
    }
}
