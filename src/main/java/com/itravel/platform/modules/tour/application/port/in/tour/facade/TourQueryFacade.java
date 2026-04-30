package com.itravel.platform.modules.tour.application.port.in.tour.facade;

import com.itravel.platform.modules.tour.application.dto.TourDTO;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.dto.TourListItemDTO;
import com.itravel.platform.modules.tour.application.query.tour.GetTourDetailHandler;
import com.itravel.platform.modules.tour.application.query.tour.GetTourHandler;
import com.itravel.platform.modules.tour.application.query.tour.GetToursHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourQueryFacade {
    GetToursHandler getToursHandler;
    GetTourDetailHandler getDetailHandler;
    GetTourHandler getTourHandler;

    public Page<TourListItemDTO> getTours(String keyword, Boolean isDeleted, String status, Long categoryId, Long departureId, Pageable pageable) {
        return getToursHandler.getTours(keyword, isDeleted, status, categoryId, departureId, pageable);
    }

    public TourDTO getTour(String id) {
        return getTourHandler.getTours(id);
    }

    public TourDetailDTO getDetail(String slugOrId) {
        return getDetailHandler.getDetail(slugOrId);
    }
}
