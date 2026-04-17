package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.location.infrastructure.persistence.repository.LocationJpaRepository;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import com.itravel.platform.modules.tour.domain.category.Category;
import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.tour.Tour;
import com.itravel.platform.modules.tour.domain.tour.TourId;
import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ItineraryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourImageJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.ItineraryMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.TourImageMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.TourMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.CategoryJpaRepository;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.TourJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourRepositoryImpl implements TourRepository {
    TourJpaRepository tourJpaRepository;
    CategoryJpaRepository categoryJpaRepository;
    LocationJpaRepository locationJpaRepository;
    ItineraryMapper itineraryMapper;
    TourImageMapper tourImageMapper;

    @Override
    public Optional<Tour> findById(TourId id) {
        return tourJpaRepository.findById(id.value())
                .map(TourMapper::toTourDomain);
    }

    @Override
    public TourDetailDTO save(Tour tour) {
        CategoryJpaEntity category = null;
        LocationJpaEntity departureLocation= null;
        LocationJpaEntity destinationLocation = null;
        List<TourImageJpaEntity> images = new ArrayList<>();
        List<ItineraryJpaEntity> itineraries = new ArrayList<>();
        if (tour.getCategoryId() != null) {
            category = categoryJpaRepository.getReferenceById(tour.getCategoryId().value());
        }
        if (tour.getDepartureLocationId() != null) {
            departureLocation = locationJpaRepository.getReferenceById(tour.getDepartureLocationId().value());
        }
        if (tour.getDestinationLocationId() != null) {
            destinationLocation = locationJpaRepository.getReferenceById(tour.getDestinationLocationId().value());
        }
        if (tour.getTourImages() != null) {
            images = tour.getTourImages().stream().map(tourImageMapper::toTourImageJpaEntity).toList();
        }
        if (tour.getItineraries() != null) {
            itineraries = tour.getItineraries().stream().map(itineraryMapper::toItineraryJpaEntity).toList();
        }

        TourJpaEntity entity = tourJpaRepository.findById(tour.getId().value())
                .orElse(new TourJpaEntity());

        TourJpaEntity finalTour = TourMapper.toTourJpaEntity(entity,tour,category, departureLocation, destinationLocation, images, itineraries);

        tourJpaRepository.save(finalTour);
        return TourMapper.toTourDetailDTO(finalTour);
    }


    @Override
    public void destroy(TourId id) {
        tourJpaRepository.findById(id.value())
                .ifPresent(tourJpaRepository::delete);
    }
}
