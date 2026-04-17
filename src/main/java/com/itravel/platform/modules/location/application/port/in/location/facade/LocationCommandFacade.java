package com.itravel.platform.modules.location.application.port.in.location.facade;

import com.itravel.platform.modules.location.application.command.location.*;
import com.itravel.platform.modules.location.application.port.in.location.*;
import com.itravel.platform.modules.location.domain.location.Location;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationCommandFacade {
    CreateLocationUseCase createLocationUseCase;
    UpdateLocationUseCase updateLocationUseCase;
    DeleteLocationUseCase deleteLocationUseCase;
    RestoreLocationUseCase restoreLocationUseCase;
    UpdateStatusLocationUseCase updateStatusLocationUseCase;
    DestroyLocationUseCase destroyLocationUseCase;

    public Location create(CreateLocationCommand command) {
        return createLocationUseCase.execute(command);
    }

    public Location update(UpdateLocationCommand command) {
        return updateLocationUseCase.execute(command);
    }

    public void delete(DeleteLocationCommand command) {
        deleteLocationUseCase.execute(command);
    }

    public void restore(RestoreLocationCommand command) {
        restoreLocationUseCase.execute(command);
    }

    public void status(UpdateStatusLocationCommand command) {
        updateStatusLocationUseCase.execute(command);
    }

    public void destroy(DeleteLocationCommand command) {
        destroyLocationUseCase.execute(command);
    }
}
