package com.itravel.platform.modules.tour.application.query.tour;

import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.out.tour.TourQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetTourDetailHandler {
    TourQueryPort queryPort;

    public TourDetailDTO getDetail(String slugOrId) {
        return queryPort.getById(slugOrId)
                .or(() -> queryPort.getBySlug(slugOrId))
                .orElseThrow(TourNotFoundException::new);
    }
}

