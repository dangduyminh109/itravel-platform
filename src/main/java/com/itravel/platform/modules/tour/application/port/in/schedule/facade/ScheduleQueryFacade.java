package com.itravel.platform.modules.tour.application.port.in.schedule.facade;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.query.schedule.GetScheduleDetailHandler;
import com.itravel.platform.modules.tour.application.query.schedule.GetScheduleListFormTourHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleQueryFacade {
    GetScheduleListFormTourHandler getScheduleListFormTourHandler;
    GetScheduleDetailHandler getScheduleDetailHandler;

    public Page<ScheduleDetailDTO> getScheduleList(Boolean isDeleted, String tourId, Pageable pageable) {
        return getScheduleListFormTourHandler.getScheduleList(isDeleted, tourId, pageable);
    }

    public ScheduleDetailDTO getDetail(String id) {
        return getScheduleDetailHandler.getDetail(id);
    }
}
