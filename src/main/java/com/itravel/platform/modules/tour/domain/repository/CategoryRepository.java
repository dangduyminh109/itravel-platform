package com.itravel.platform.modules.tour.domain.repository;

import com.itravel.platform.modules.tour.domain.aggregate.Category;
import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository {
    Optional<Category> findById(CategoryId id);
    boolean existsByName(CategoryName name);
    boolean existsByNameAndIdNot(CategoryName name, CategoryId id);
    Page<Category> getCategories(String keyword, Pageable pageable, Boolean isDeleted, CategoryStatus status);
    void save(Category category);
    void destroy(CategoryId id);
}

