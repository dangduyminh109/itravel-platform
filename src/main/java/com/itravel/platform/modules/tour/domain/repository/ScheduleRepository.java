package com.itravel.platform.modules.tour.domain.repository;

import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository {
    Optional<Schedule> findById(ScheduleId id);
    void save(Schedule schedule);
    void destroy(ScheduleId id);
}
