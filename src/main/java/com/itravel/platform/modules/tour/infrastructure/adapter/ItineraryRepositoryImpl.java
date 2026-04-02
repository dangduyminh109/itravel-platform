package com.itravel.platform.modules.tour.infrastructure.adapter;

import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.itinerary.ItineraryId;
import com.itravel.platform.modules.tour.domain.repository.ItineraryRepository;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ItineraryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.mapper.ItineraryMapper;
import com.itravel.platform.modules.tour.infrastructure.persistence.repository.ItineraryJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItineraryRepositoryImpl implements ItineraryRepository {
    ItineraryJpaRepository itineraryJpaRepository;
    ItineraryMapper mapper;

    @Override
    public Optional<Itinerary> findById(ItineraryId id) {
        Optional<ItineraryJpaEntity> entity = itineraryJpaRepository.findById(id.value());
        return entity.map(ItineraryMapper::toItineraryDomain);
    }

    @Override
    public void save(Itinerary itinerary) {
        ItineraryJpaEntity entity = mapper.toItineraryJpaEntity(itinerary);
        itineraryJpaRepository.save(entity);
    }

    @Override
    public void destroy(ItineraryId id) {
        itineraryJpaRepository.findById(id.value())
                .ifPresent(itineraryJpaRepository::delete);
    }
}

