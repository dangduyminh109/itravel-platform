package com.itravel.platform.modules.tour.domain.repository;

import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import com.itravel.platform.modules.tour.domain.tour.TourImageId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourImageRepository {
    Optional<TourImage> findById(TourImageId id);
    void save(TourImage tourImage);
    void destroy(TourImageId id);
}
