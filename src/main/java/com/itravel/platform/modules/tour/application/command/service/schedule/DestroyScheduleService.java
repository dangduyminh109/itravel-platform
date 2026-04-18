package com.itravel.platform.modules.tour.application.command.service.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.DeleteScheduleCommand;
import com.itravel.platform.modules.tour.application.port.in.schedule.DestroyScheduleUseCase;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyScheduleService implements DestroyScheduleUseCase {
    ScheduleRepository repository;

    @Override
    @Transactional
    public void execute(DeleteScheduleCommand command) {
        repository.destroy(command.id());
    }
}
