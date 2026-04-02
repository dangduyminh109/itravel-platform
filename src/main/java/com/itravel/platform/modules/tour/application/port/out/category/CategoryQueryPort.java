package com.itravel.platform.modules.tour.application.port.out.category;

import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryQueryPort {
    Optional<Category> findById(Long id);
    Optional<CategoryDetailDTO> getById(Long id);
    Page<CategoryDetailDTO> getCategories(String keyword, Pageable pageable, Boolean isDeleted, String status);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
