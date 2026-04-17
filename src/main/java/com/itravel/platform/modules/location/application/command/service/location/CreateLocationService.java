package com.itravel.platform.modules.location.application.command.service.location;

import com.itravel.platform.modules.location.application.command.location.CreateLocationCommand;
import com.itravel.platform.modules.location.application.exception.LocationNameExistedException;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.application.port.in.location.CreateLocationUseCase;
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
public class CreateLocationService implements CreateLocationUseCase {
    LocationRepository locationRepository;

    @Override
    @Transactional
    public Location execute(CreateLocationCommand command) {
        Location parent = null;
        if (command.parentId() != null) {
            parent = locationRepository.findById(command.parentId(), false, false, 0)
                    .orElseThrow(LocationNotFoundException::new);
        }

        if (locationRepository.existsByName(command.name())) {
            throw new LocationNameExistedException();
        }

        Location location = Location.create(
                command.name(),
                command.type(),
                parent,
                command.status()
        );
        
        locationRepository.save(location);
        return location;
    }
}
