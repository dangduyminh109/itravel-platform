package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ItineraryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryJpaRepository extends JpaRepository<ItineraryJpaEntity, Long> {
}

