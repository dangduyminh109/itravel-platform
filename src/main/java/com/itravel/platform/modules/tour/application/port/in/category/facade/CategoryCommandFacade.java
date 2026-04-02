package com.itravel.platform.modules.tour.application.port.in.category.facade;

import com.itravel.platform.modules.tour.application.command.model.category.*;
import com.itravel.platform.modules.tour.application.port.in.category.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryCommandFacade {
    CreateCategoryUseCase createUseCase;
    UpdateCategoryUseCase updateUseCase;
    DeleteCategoryUseCase deleteUseCase;
    RestoreCategoryUseCase restoreUseCase;
    DestroyCategoryUseCase destroyUseCase;
    UpdateStatusCategoryUseCase statusUseCase;

    public void create(CreateCategoryCommand command) {
        createUseCase.execute(command);
    }

    public void update(UpdateCategoryCommand command) {
        updateUseCase.execute(command);
    }

    public void delete(DeleteCategoryCommand command) {
        deleteUseCase.execute(command);
    }

    public void restore(RestoreCategoryCommand command) {
        restoreUseCase.execute(command);
    }

    public void destroy(DeleteCategoryCommand command) {
        destroyUseCase.execute(command);
    }

    public void updateStatus(UpdateStatusCategoryCommand command) {
        statusUseCase.execute(command);
    }
}
