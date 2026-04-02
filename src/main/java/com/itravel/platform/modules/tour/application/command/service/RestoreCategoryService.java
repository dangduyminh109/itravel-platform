package com.itravel.platform.modules.tour.application.command.service;

import com.itravel.platform.modules.tour.application.command.model.category.RestoreCategoryCommand;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.category.RestoreCategoryUseCase;
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
public class RestoreCategoryService implements RestoreCategoryUseCase {
    CategoryRepository categoryRepository;
    CategoryQueryPort queryPort;

    @Transactional
    public void execute(RestoreCategoryCommand command) {
        Category category = queryPort.findById(command.id().value())
                .orElseThrow(CategoryNotFoundException::new);
        category.restore();
        categoryRepository.save(category);
    }
}
