package com.itravel.platform.modules.tour.application.command.service;

import com.itravel.platform.modules.tour.application.command.model.category.DeleteCategoryCommand;
import com.itravel.platform.modules.tour.application.port.in.category.DestroyCategoryUseCase;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyCategoryService implements DestroyCategoryUseCase {
    CategoryRepository categoryRepository;

    @Transactional
    public void execute(DeleteCategoryCommand command) {
        categoryRepository.destroy(command.id());
    }
}
