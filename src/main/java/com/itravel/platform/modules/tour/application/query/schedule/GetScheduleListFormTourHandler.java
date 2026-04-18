package com.itravel.platform.modules.tour.application.query.schedule;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetScheduleListFormTourHandler {
    ScheduleQueryPort queryPort;

    public Page<ScheduleDetailDTO> getScheduleList(Boolean isDeleted, String tourId, Pageable pageable) {
        return queryPort.getScheduleListFromTour(isDeleted, tourId, pageable);
    }
}
