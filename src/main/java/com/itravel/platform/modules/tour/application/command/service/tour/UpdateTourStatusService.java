package com.itravel.platform.modules.tour.application.command.service.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.UpdateTourStatusCommand;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.tour.UpdateTourStatusUseCase;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import com.itravel.platform.modules.tour.domain.tour.Tour;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateTourStatusService implements UpdateTourStatusUseCase {
    TourRepository repository;

    @Transactional
    public void execute(UpdateTourStatusCommand command) {
        Tour tour = repository.findById(command.id())
                .orElseThrow(TourNotFoundException::new);
        tour.updateStatus(command.status());
        repository.save(tour);
    }
}

