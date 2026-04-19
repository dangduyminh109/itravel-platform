package com.itravel.platform.modules.tour.application.port.out.schedule;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.tour.TourId;

import java.util.Optional;

public interface ScheduleRepository {
    Optional<Schedule> findById(ScheduleId id);
    ScheduleDetailDTO save(Schedule schedule);
    void destroy(ScheduleId id);
    void deleteByTourId(TourId tourId);
}
