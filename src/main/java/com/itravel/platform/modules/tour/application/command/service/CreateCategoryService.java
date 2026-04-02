package com.itravel.platform.modules.tour.application.command.service;

import com.itravel.platform.modules.tour.application.command.model.category.CreateCategoryCommand;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.exception.CategoryNameExistedException;
import com.itravel.platform.modules.tour.application.mapper.CategoryMapper;
import com.itravel.platform.modules.tour.application.port.in.category.CreateCategoryUseCase;
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
public class CreateCategoryService implements CreateCategoryUseCase {
    CategoryRepository repository;
    CategoryQueryPort queryPort;
    CategoryMapper mapper;

    @Transactional
    public CategoryDetailDTO execute(CreateCategoryCommand command) {
        if (queryPort.existsByName(command.name().value())) {
            throw new CategoryNameExistedException();
        }

        Category category = Category.create(
                command.name(),
                command.description(),
                command.status()
        );
        repository.save(category);

        return mapper.toCategoryDetailDTO(category);
    }
}
