package com.itravel.platform.modules.tour.application.port.out.schedule;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ScheduleQueryPort {
    Page<ScheduleDetailDTO> getScheduleListFromTour(Boolean isDeleted, String tourId, Pageable pageable);
    Optional<ScheduleDetailDTO> getById(String id);
}
