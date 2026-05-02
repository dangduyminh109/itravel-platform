package com.itravel.platform.modules.tour.application.port.in.schedule;

public interface UnlockScheduleInventoryUseCase {
    void execute(String scheduleId, int quantity);
}
