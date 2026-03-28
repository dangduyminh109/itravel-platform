package com.itravel.platform.modules.tour.application.handler;

import com.itravel.platform.modules.tour.application.command.location.CreateCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.DeleteCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.UpdateCategoryCommand;
import com.itravel.platform.modules.tour.application.command.location.UpdateStatusCategoryCommand;
import com.itravel.platform.modules.tour.application.exception.CategoryNameExistedException;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.domain.aggregate.Category;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryCommandHandler {
    CategoryRepository categoryRepository;

    @Transactional
    public Category create(CreateCategoryCommand command) {
        if (categoryRepository.existsByName(command.name())) {
            throw new CategoryNameExistedException();
        }

        Category category = Category.create(
                command.name(),
                command.description(),
                command.status()
        );
        categoryRepository.save(category);
        return category;
    }

    @Transactional
    public Category update(UpdateCategoryCommand command) {
        Category category = categoryRepository.findById(command.id())
                .orElseThrow(CategoryNotFoundException::new);

        if (categoryRepository.existsByNameAndIdNot(command.name(), category.getId())) {
            throw new CategoryNameExistedException();
        }

        category.updateName(command.name());
        category.updateDescription(command.description());
        category.updateStatus(command.status());
        categoryRepository.save(category);
        return category;
    }

    @Transactional
    public void status(UpdateStatusCategoryCommand command) {
        Category category = categoryRepository.findById(command.id())
                .orElseThrow(CategoryNotFoundException::new);
        category.updateStatus(command.status());
        categoryRepository.save(category);
    }

    @Transactional
    public void delete(DeleteCategoryCommand command) {
        Category category = categoryRepository.findById(command.id())
                .orElseThrow(CategoryNotFoundException::new);
        category.softDelete();
        categoryRepository.save(category);
    }
    @Transactional
    public void restore(CategoryId id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        category.restore();
        categoryRepository.save(category);
    }

    @Transactional
    public void destroy(DeleteCategoryCommand command) {
        categoryRepository.destroy(command.id());
    }
}
