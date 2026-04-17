package com.itravel.platform.modules.location.application.command.service.location;

import com.itravel.platform.modules.location.application.command.location.UpdateStatusLocationCommand;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.application.port.in.location.UpdateStatusLocationUseCase;
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
public class UpdateStatusLocationService implements UpdateStatusLocationUseCase {
    LocationRepository locationRepository;

    @Override
    @Transactional
    public void execute(UpdateStatusLocationCommand command) {
        Location location = locationRepository.findById(command.id(), false, false, 0)
                .orElseThrow(LocationNotFoundException::new);
        location.updateStatus(command.status());
        locationRepository.updateStatus(location);
    }
}
