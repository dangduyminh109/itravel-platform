package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.CreateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.in.schedule.CreateScheduleUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleSeats;
import com.itravel.platform.modules.tour.domain.tour.Tour;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateScheduleService implements CreateScheduleUseCase {
    ScheduleRepository repository;
    TourRepository tourRepository;

    @Override
    public ScheduleDetailDTO execute(CreateScheduleCommand command) {
        Tour tour = tourRepository.findById(command.tourId())
                .orElseThrow(TourNotFoundException::new);

        ScheduleSeats seats = new ScheduleSeats(command.totalSeats(), 0, 0);
        
        BigDecimal surcharge = command.surcharge() != null ? command.surcharge() : BigDecimal.ZERO;

        Schedule schedule = Schedule.create(
                command.departureDate(),
                seats,
                command.pricing(),
                surcharge,
                command.status(),
                command.tourId(),
                tour.getParticipantLimit().minParticipants()
        );
        return repository.save(schedule);
    }
}
