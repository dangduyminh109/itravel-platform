package com.itravel.platform.modules.tour.application.command.service.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.DeleteTourCommand;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.tour.DeleteTourUseCase;
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
public class DeleteTourService implements DeleteTourUseCase {
    TourRepository repository;

    @Transactional
    public void execute(DeleteTourCommand command) {
        Tour tour = repository.findById(command.id())
                .orElseThrow(TourNotFoundException::new);
        tour.softDelete();
        repository.save(tour);
    }
}