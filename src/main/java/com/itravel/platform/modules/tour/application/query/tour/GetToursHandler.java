package com.itravel.platform.modules.tour.application.query.tour;

import com.itravel.platform.modules.tour.application.dto.TourListItemDTO;
import com.itravel.platform.modules.tour.application.port.out.tour.TourQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetToursHandler {
    TourQueryPort queryPort;

    public Page<TourListItemDTO> getTours(String keyword, Boolean isDeleted, String status, Long categoryId, Long departureId, BigDecimal maxPrice, Pageable pageable) {
        return queryPort.getTours(keyword, isDeleted, status, categoryId, departureId, maxPrice, pageable);
    }
}
