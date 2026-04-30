package com.itravel.platform.modules.tour.application.query.tour;

import com.itravel.platform.modules.tour.application.dto.TourDTO;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.out.tour.TourQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetTourHandler {
    TourQueryPort queryPort;

    public TourDTO getTours(String id) {
        return queryPort.getTour(id)
                .orElseThrow(TourNotFoundException::new);
    }
}
