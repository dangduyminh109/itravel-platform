package com.itravel.platform.modules.tour.domain.repository;

import com.itravel.platform.modules.tour.domain.aggregate.Itinerary;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItineraryRepository {
    Optional<Itinerary> findById(ItineraryId id);
    void save(Itinerary itinerary);
    void destroy(ItineraryId id);
}

