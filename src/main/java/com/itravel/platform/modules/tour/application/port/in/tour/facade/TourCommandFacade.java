package com.itravel.platform.modules.tour.application.port.in.tour.facade;

import com.itravel.platform.modules.tour.application.command.model.tour.*;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.port.in.tour.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourCommandFacade {
    CreateTourUseCase createUseCase;
    UpdateTourUseCase updateUseCase;
    UpdateTourStatusUseCase statusUseCase;
    DeleteTourUseCase deleteUseCase;
    DestroyTourUseCase destroyUseCase;
    RestoreTourUseCase restoreUseCase;

    public TourDetailDTO create(CreateTourCommand command) {
        return createUseCase.execute(command);
    }

    public TourDetailDTO update(UpdateTourCommand command) {
        return updateUseCase.execute(command);
    }

    public void updateStatus(UpdateTourStatusCommand command) {
        statusUseCase.execute(command);
    }

    public void delete(DeleteTourCommand command) {
        deleteUseCase.execute(command);
    }

    public void destroy(DeleteTourCommand command) {
        destroyUseCase.execute(command);
    }

    public void restore(RestoreTourCommand command) {
        restoreUseCase.execute(command);
    }
}
