package com.itravel.platform.modules.tour.application.command.model.tour;

import com.itravel.platform.modules.tour.domain.tour.TourId;
import com.itravel.platform.modules.tour.domain.tour.TourStatus;

public record UpdateTourStatusCommand(
        TourId id,
        TourStatus status
) {}

