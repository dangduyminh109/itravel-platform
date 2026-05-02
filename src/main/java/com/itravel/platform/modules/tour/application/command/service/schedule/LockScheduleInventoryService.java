package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.port.in.schedule.LockScheduleInventoryUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.schedule.exception.NotEnoughAvailableSeatsException;
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

    @Override
    @Transactional
    public boolean execute(String scheduleId, int quantity) {
        return repository.findById(new ScheduleId(scheduleId))
                .map(schedule -> {
                    try {
                        schedule.lockSeats(quantity);
                        repository.save(schedule);
                        return true;
                    } catch (NotEnoughAvailableSeatsException e) {
                        return false;
                    }
                })
                .orElse(false);
    }
}
