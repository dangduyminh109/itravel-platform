package com.itravel.platform.modules.tour.application.command.service.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.RestoreTourCommand;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.tour.RestoreTourUseCase;
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
public class RestoreTourService implements RestoreTourUseCase {
    TourRepository repository;

    @Override
    @Transactional
    public void execute(RestoreTourCommand command) {
        Tour tour = repository.findById(command.id())
                .orElseThrow(TourNotFoundException::new);
        tour.restore();
        repository.save(tour);
    }
}