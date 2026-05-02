package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.port.in.schedule.UnlockScheduleInventoryUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UnlockScheduleInventoryService implements UnlockScheduleInventoryUseCase {
    ScheduleRepository repository;

    @Override
    @Transactional
    public void execute(String scheduleId, int quantity) {
        repository.findById(new ScheduleId(scheduleId))
                .ifPresent(schedule -> {
                    schedule.unlockSeats(quantity);
                    repository.save(schedule);
                });
    }
}
