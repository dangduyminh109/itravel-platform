package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleQueryPort;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.ScheduleMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.ScheduleJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleQueryAdapter implements ScheduleQueryPort {
    ScheduleJpaRepository repository;

    @Override
    public Page<ScheduleDetailDTO> getScheduleListFromTour(Boolean isDeleted, String tourId, Pageable pageable) {
        return repository.getScheduleListFromTour(isDeleted,tourId,pageable)
                .map(ScheduleMapper::toScheduleDetailDTO);
    }

    @Override
    public Optional<ScheduleDetailDTO> getById(Long id) {
        return repository.findById(id)
                .map(ScheduleMapper::toScheduleDetailDTO);
    }
}
