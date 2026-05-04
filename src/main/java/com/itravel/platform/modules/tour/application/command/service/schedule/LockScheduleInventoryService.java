package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.exception.ScheduleNotFoundException;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.schedule.LockScheduleInventoryUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.tour.Tour;
import com.itravel.platform.modules.tour.domain.tour.exception.TourMinParticipantsNotMetException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LockScheduleInventoryService implements LockScheduleInventoryUseCase {
    ScheduleRepository repository;
    TourRepository tourRepository;

    @Override
    @Transactional
    public boolean execute(String scheduleId, int quantity) {
        Schedule schedule = repository.findById(new ScheduleId(scheduleId))
                .orElseThrow(ScheduleNotFoundException::new);
        Tour tour = tourRepository.findById(schedule.getTourId())
                .orElseThrow(TourNotFoundException::new);
        if (!tour.verifyMinParticipant(quantity)) {
            throw new TourMinParticipantsNotMetException();
        }
        schedule.lockSeats(quantity);
        repository.save(schedule);
        return true;
    }
}
