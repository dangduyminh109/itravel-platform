package com.itravel.platform.modules.tour.application.port.out.tour;

import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.domain.tour.Tour;
import com.itravel.platform.modules.tour.domain.tour.TourId;

import java.util.Optional;

public interface TourRepository {
    Optional<Tour> findById(TourId id);
    TourDetailDTO save(Tour tour);
    void destroy(TourId id);
}

