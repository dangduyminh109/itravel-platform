package com.itravel.platform.modules.location.application.command.service.location;

import com.itravel.platform.modules.location.application.command.location.DeleteLocationCommand;
import com.itravel.platform.modules.location.application.port.in.location.DestroyLocationUseCase;
import com.itravel.platform.modules.location.application.port.out.location.LocationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyLocationService implements DestroyLocationUseCase {
    LocationRepository locationRepository;

    @Override
    @Transactional
    public void execute(DeleteLocationCommand command) {
        locationRepository.destroy(command.id());
    }
}
