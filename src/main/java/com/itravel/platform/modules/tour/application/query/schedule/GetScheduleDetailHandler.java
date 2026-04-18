package com.itravel.platform.modules.tour.application.query.schedule;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.exception.ScheduleNotFoundException;
import com.itravel.platform.modules.tour.application.port.out.schedule.ScheduleQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetScheduleDetailHandler {
    ScheduleQueryPort queryPort;

    public ScheduleDetailDTO getDetail(Long id) {
        return queryPort.getById(id)
                .orElseThrow(ScheduleNotFoundException::new);
    }
}
