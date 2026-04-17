package com.itravel.platform.modules.tour.application.port.in.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.UpdateTourCommand;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;

public interface UpdateTourUseCase {
    TourDetailDTO execute(UpdateTourCommand command);
}

