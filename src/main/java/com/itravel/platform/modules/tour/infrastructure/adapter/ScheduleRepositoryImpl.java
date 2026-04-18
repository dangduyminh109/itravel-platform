package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ScheduleJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.ScheduleMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.ScheduleJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleRepositoryImpl implements ScheduleRepository {
    ScheduleJpaRepository scheduleJpaRepository;
    ScheduleMapper mapper;

    @Override
    public Optional<Schedule> findById(ScheduleId id) {
        Optional<ScheduleJpaEntity> entity = scheduleJpaRepository.findById(id.value());
        return entity.map(ScheduleMapper::toScheduleDomain);
    }

    @Override
    public ScheduleDetailDTO save(Schedule schedule) {
        ScheduleJpaEntity entity = mapper.toScheduleJpaEntity(schedule);
        return ScheduleMapper.toScheduleDetailDTO(scheduleJpaRepository.save(entity));
    }

    @Override
    public void destroy(ScheduleId id) {
        scheduleJpaRepository.findById(id.value())
                .ifPresent(scheduleJpaRepository::delete);
    }
}

