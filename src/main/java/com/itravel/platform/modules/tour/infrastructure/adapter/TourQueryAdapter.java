package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.dto.TourListItemDTO;
import com.itravel.platform.modules.tour.application.port.out.tour.TourQueryPort;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.TourMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.TourJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourQueryAdapter implements TourQueryPort {
    TourJpaRepository repository;

    @Override
    public Page<TourListItemDTO> getTours(String keyword, Boolean isDeleted, String status, Long categoryId, Long departureId, BigDecimal maxPrice, Pageable pageable) {
        return repository.getTours(keyword, isDeleted, status, categoryId, departureId, maxPrice, pageable)
                .map(TourMapper::toTourListItemDTO);
    }

    @Override
    public Optional<TourDetailDTO> getById(String id) {
        Optional<TourJpaEntity> entity = repository.findById(id);
        return entity.map(TourMapper::toTourDetailDTO);
    }

    @Override
    public Optional<TourDetailDTO> getBySlug(String slug) {
        Optional<TourJpaEntity> entity = repository.findBySlug(slug);
        return entity.map(TourMapper::toTourDetailDTO);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, String id) {
        return repository.existsByNameAndIdNot(name, id);
    }
}
