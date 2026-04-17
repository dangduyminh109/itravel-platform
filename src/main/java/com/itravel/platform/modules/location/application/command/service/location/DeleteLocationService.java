package com.itravel.platform.modules.location.application.command.service.location;

import com.itravel.platform.modules.location.application.command.location.DeleteLocationCommand;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.application.port.in.location.DeleteLocationUseCase;
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
public class DeleteLocationService implements DeleteLocationUseCase {
    LocationRepository locationRepository;

    @Override
    @Transactional
    public void execute(DeleteLocationCommand command) {
        Location location = locationRepository.findById(command.id(), false, true, 1)
                .orElseThrow(LocationNotFoundException::new);
        location.softDelete();
        locationRepository.delete(location);
    }
}
