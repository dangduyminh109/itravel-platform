package com.itravel.platform.modules.tour.application.command.service.tour;

import com.itravel.platform.modules.tour.application.command.model.tour.DeleteTourCommand;
import com.itravel.platform.modules.tour.application.port.in.tour.DestroyTourUseCase;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyTourService implements DestroyTourUseCase {
    TourRepository repository;

    @Override
    @Transactional
    public void execute(DeleteTourCommand command) {
        repository.destroy(command.id());
    }
}

