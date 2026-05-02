package com.itravel.platform.modules.tour.application.port.in.schedule;

public interface LockScheduleInventoryUseCase {
    boolean execute(String scheduleId, int quantity);
}
