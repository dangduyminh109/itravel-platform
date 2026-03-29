package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.domain.aggregate.Schedule;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ScheduleId;
import com.itravel.platform.modules.tour.domain.repository.ScheduleRepository;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ScheduleJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.ScheduleMapper;
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
    public void save(Schedule schedule) {
        ScheduleJpaEntity entity = mapper.toScheduleJpaEntity(schedule);
        scheduleJpaRepository.save(entity);
    }

    @Override
    public void destroy(ScheduleId id) {
        scheduleJpaRepository.findById(id.value())
                .ifPresent(scheduleJpaRepository::delete);
    }
}

