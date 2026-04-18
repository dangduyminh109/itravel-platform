package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.CreateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.in.schedule.CreateScheduleUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateScheduleService implements CreateScheduleUseCase {
    ScheduleRepository repository;

    @Override
    public ScheduleDetailDTO execute(CreateScheduleCommand command) {
        Schedule schedule = Schedule.create(
                command.departureDate(),
                command.availableSeats(),
                command.surcharge(),
                command.status(),
                command.tourId()
        );

        return repository.save(schedule);
    }
}
