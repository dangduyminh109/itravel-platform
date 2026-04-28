package com.itravel.platform.modules.tour.application.port.out.tour;

import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.dto.TourListItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface TourQueryPort {
    Page<TourListItemDTO> getTours(String keyword, Boolean isDeleted, String status, Long categoryId, Long departureId, Pageable pageable);
    Optional<TourDetailDTO> getById(String id);
    Optional<TourDetailDTO> getBySlug(String slug);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
}
