package com.itravel.platform.modules.tour.application.command.service.category;

import com.itravel.platform.modules.tour.application.command.model.category.UpdateStatusCategoryCommand;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.category.UpdateStatusCategoryUseCase;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryRepository;
import com.itravel.platform.modules.tour.domain.category.Category;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateStatusCategoryService implements UpdateStatusCategoryUseCase {
    CategoryRepository repository;
    CategoryQueryPort queryPort;

    @Transactional
    public void execute(UpdateStatusCategoryCommand command) {
        Category category = queryPort.findById(command.id().value())
                .orElseThrow(CategoryNotFoundException::new);
        category.updateStatus(command.status());
        repository.save(category);
    }
}
