package com.itravel.platform.modules.tour.application.command.service.category;

import com.itravel.platform.modules.tour.application.command.model.category.UpdateCategoryCommand;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.exception.CategoryNameExistedException;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.category.UpdateCategoryUseCase;
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
public class UpdateCategoryService implements UpdateCategoryUseCase {
    CategoryRepository repository;
    CategoryQueryPort queryPort;

    @Transactional
    public CategoryDetailDTO execute(UpdateCategoryCommand command) {
        Category category = queryPort.findById(command.id().value())
                .orElseThrow(CategoryNotFoundException::new);

        if (queryPort.existsByNameAndIdNot(command.name().value(), command.id().value())) {
            throw new CategoryNameExistedException();
        }

        category.updateName(command.name());
        category.updateDescription(command.description());
        category.updateStatus(command.status());
        return repository.save(category);
    }
}
