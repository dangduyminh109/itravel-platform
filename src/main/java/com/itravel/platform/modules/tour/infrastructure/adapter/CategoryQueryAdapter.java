package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;
import com.itravel.platform.modules.tour.application.dto.CategoryGeneralInfoDTO;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import com.itravel.platform.modules.tour.domain.category.Category;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.CategoryMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.CategoryJpaRepository;
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
public class CategoryQueryAdapter implements CategoryQueryPort {
    CategoryJpaRepository repository;

    @Override
    public Optional<Category> findById(Long id) {
        Optional<CategoryJpaEntity> entity = repository.findById(id);
        return entity.map(CategoryMapper::toCategoryDomain);
    }

    @Override
    public Optional<CategoryDetailDTO> getById(Long id) {
        Optional<CategoryJpaEntity> entity = repository.findById(id);
        return entity.map(CategoryMapper::toCategoryDetailDTO);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return repository.existsByNameAndIdNot(name, id);
    }

    @Override
    public CategoryGeneralInfoDTO getCategoryGeneralInfoDTO() {
        return repository.getCategoryGeneralInfoDTO();
    }

    @Override
    public Page<CategoryDetailDTO> getCategories(String keyword, Pageable pageable, Boolean isDeleted, String status) {
        return repository
                .getCategories(keyword, pageable, isDeleted, status)
                .map(CategoryMapper::toCategoryDetailDTO);
    }
}
