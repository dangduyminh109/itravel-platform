package com.itravel.platform.modules.tour.application.command.model.tour;

import com.itravel.platform.modules.tour.domain.tour.TourId;

public record DeleteTourCommand(
        TourId id
) {}