package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.domain.repository.TourImageRepository;
import com.itravel.platform.modules.tour.domain.tour.TourImageId;
import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourImageJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.TourImageMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.TourImageJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourImageRepositoryImpl implements TourImageRepository {
    TourImageJpaRepository tourImageJpaRepository;
    TourImageMapper mapper;

    @Override
    public Optional<TourImage> findById(TourImageId id) {
        Optional<TourImageJpaEntity> entity = tourImageJpaRepository.findById(id.value());
        return entity.map(TourImageMapper::toTourImageDomain);
    }

    @Override
    public void save(TourImage tourImage) {
        TourImageJpaEntity entity = mapper.toTourImageJpaEntity(tourImage);
        tourImageJpaRepository.save(entity);
    }

    @Override
    public void destroy(TourImageId id) {
        tourImageJpaRepository.findById(id.value())
                .ifPresent(tourImageJpaRepository::delete);
    }
}

