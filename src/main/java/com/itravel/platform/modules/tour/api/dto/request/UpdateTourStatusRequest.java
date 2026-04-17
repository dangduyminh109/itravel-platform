package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTourStatusRequest(
        @NotNull(message = "TOUR_STATUS_CANNOT_BE_BLANK")
        TourStatus status
) {}

