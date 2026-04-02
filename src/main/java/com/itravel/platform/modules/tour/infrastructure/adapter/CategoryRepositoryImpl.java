package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.application.port.out.category.CategoryRepository;
import com.itravel.platform.modules.tour.domain.category.Category;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.CategoryMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.CategoryJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryRepositoryImpl implements CategoryRepository {
    CategoryJpaRepository repository;
    CategoryMapper mapper;

    @Override
    public void save(Category category) {
        CategoryJpaEntity entity = mapper.toCategoryJpaEntity(category);
        repository.save(entity);
    }

    @Override
    public void destroy(CategoryId id) {
        repository.findById(id.value())
                .ifPresent(repository::delete);
    }
}

