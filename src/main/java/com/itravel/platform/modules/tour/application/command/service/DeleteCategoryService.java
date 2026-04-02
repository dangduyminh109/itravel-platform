package com.itravel.platform.modules.tour.application.command.service;

import com.itravel.platform.modules.tour.application.command.model.category.DeleteCategoryCommand;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.category.DeleteCategoryUseCase;
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
public class DeleteCategoryService implements DeleteCategoryUseCase {
    CategoryRepository categoryRepository;
    CategoryQueryPort queryPort;

    @Transactional
    public void execute(DeleteCategoryCommand command) {
        Category category = queryPort.findById(command.id().value())
                .orElseThrow(CategoryNotFoundException::new);
        category.softDelete();
        categoryRepository.save(category);
    }
}
