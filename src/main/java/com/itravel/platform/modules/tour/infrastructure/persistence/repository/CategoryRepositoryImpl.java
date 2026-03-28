package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.domain.aggregate.Category;
import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryName;
import com.itravel.platform.modules.tour.domain.repository.CategoryRepository;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.CategoryMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryRepositoryImpl implements CategoryRepository {
    CategoryJpaRepository categoryJpaRepository;
    CategoryMapper mapper;

    @Override
    public Optional<Category> findById(CategoryId id) {
        Optional<CategoryJpaEntity> entity = categoryJpaRepository.findById(id.value());
        return entity.map(CategoryMapper::toCategoryDomain);
    }

    @Override
    public boolean existsByName(CategoryName name) {
        return categoryJpaRepository.existsByName(name.value());
    }

    @Override
    public boolean existsByNameAndIdNot(CategoryName name, CategoryId id) {
        return categoryJpaRepository.existsByNameAndIdNot(name.value(), id.value());
    }

    @Override
    public Page<Category> getCategories(String keyword, Pageable pageable, Boolean isDeleted, CategoryStatus status) {
        return categoryJpaRepository
                .getCategories(keyword, pageable, isDeleted, status)
                .map(CategoryMapper::toCategoryDomain);
    }

    @Override
    public void save(Category category) {
        CategoryJpaEntity entity = mapper.toCategoryJpaEntity(category);
        categoryJpaRepository.save(entity);
    }

    @Override
    public void destroy(CategoryId id) {
        categoryJpaRepository.findById(id.value())
                .ifPresent(categoryJpaRepository::delete);
    }
}

