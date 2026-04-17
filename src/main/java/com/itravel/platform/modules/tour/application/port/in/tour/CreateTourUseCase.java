package com.itravel.platform.modules.tour.application.port.in.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.CreateTourCommand;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;

public interface CreateTourUseCase {
    TourDetailDTO execute(CreateTourCommand command);
}