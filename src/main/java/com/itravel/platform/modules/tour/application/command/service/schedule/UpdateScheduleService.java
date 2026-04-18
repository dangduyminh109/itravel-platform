package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.UpdateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.exception.ScheduleNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.schedule.UpdateScheduleUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateScheduleService implements UpdateScheduleUseCase {
    ScheduleRepository repository;

    @Transactional
    @Override
    public ScheduleDetailDTO execute(UpdateScheduleCommand command) {
        Schedule schedule = repository.findById(command.id())
                .orElseThrow(ScheduleNotFoundException::new);

        if (command.departureDate() != null) {
            schedule.updateDepartureDate(command.departureDate());
        }

        if (command.availableSeats() != null) {
            schedule.updateAvailableSeats(command.availableSeats());
        }

        if (command.surcharge() != null) {
            schedule.updateSurcharge(command.surcharge());
        }

        if (command.status() != null) {
            schedule.updateStatus(command.status());
        }

        return repository.save(schedule);
    }
}
