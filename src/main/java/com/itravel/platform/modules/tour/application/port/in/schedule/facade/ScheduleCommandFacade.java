package com.itravel.platform.modules.tour.application.port.in.schedule.facade;

import com.itravel.platform.modules.tour.application.command.model.schedule.CreateScheduleCommand;
import com.itravel.platform.modules.tour.application.command.model.schedule.DeleteScheduleCommand;
import com.itravel.platform.modules.tour.application.command.model.schedule.UpdateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.port.in.schedule.CreateScheduleUseCase;
import com.itravel.platform.modules.tour.application.port.in.schedule.DestroyScheduleUseCase;
import com.itravel.platform.modules.tour.application.port.in.schedule.LockScheduleInventoryUseCase;
import com.itravel.platform.modules.tour.application.port.in.schedule.UnlockScheduleInventoryUseCase;
import com.itravel.platform.modules.tour.application.port.in.schedule.UpdateScheduleUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleCommandFacade {
    CreateScheduleUseCase createUseCase;
    UpdateScheduleUseCase updateUseCase;
    DestroyScheduleUseCase destroyUseCase;
    LockScheduleInventoryUseCase lockInventoryUseCase;
    UnlockScheduleInventoryUseCase unlockInventoryUseCase;

    public ScheduleDetailDTO create(CreateScheduleCommand command) {
        return createUseCase.execute(command);
    }

    public ScheduleDetailDTO update(UpdateScheduleCommand command) {
        return updateUseCase.execute(command);
    }

    public void destroy(DeleteScheduleCommand command) {
        destroyUseCase.execute(command);
    }

    public boolean lockInventory(String scheduleId, int quantity) {
        return lockInventoryUseCase.execute(scheduleId, quantity);
    }

    public void unlockInventory(String scheduleId, int quantity) {
        unlockInventoryUseCase.execute(scheduleId, quantity);
    }
}
