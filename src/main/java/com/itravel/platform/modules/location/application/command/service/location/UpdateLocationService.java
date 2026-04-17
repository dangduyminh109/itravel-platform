package com.itravel.platform.modules.location.application.command.service.location;

import com.itravel.platform.modules.location.application.command.location.UpdateLocationCommand;
import com.itravel.platform.modules.location.application.exception.LocationNameExistedException;
import com.itravel.platform.modules.location.application.exception.LocationNotFoundException;
import com.itravel.platform.modules.location.application.port.in.location.UpdateLocationUseCase;
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
public class UpdateLocationService implements UpdateLocationUseCase {
    LocationRepository locationRepository;

    @Override
    @Transactional
    public Location execute(UpdateLocationCommand command) {
        Location location = locationRepository.findById(command.id(), false, true, 0)
                .orElseThrow(LocationNotFoundException::new);
        
        location.updateName(command.name());
        location.updateStatus(command.status());
        location.updateType(command.type());

        if (locationRepository.existsByNameAndIdNot(command.name(), location.getId())) {
            throw new LocationNameExistedException();
        }

        if (command.parentId() != null) {
            Location parent = locationRepository.findById(command.parentId(), false, false, 0)
                    .orElseThrow(LocationNotFoundException::new);
            location.updateParent(parent);
        } else {
            location.updateParent(null);
        }

        Location updated = locationRepository.update(location);
        if (updated == null) {
            throw new LocationNotFoundException();
        }
        return updated;
    }
}
