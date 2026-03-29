package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourImageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourImageJpaRepository extends JpaRepository<TourImageJpaEntity, Long> {
}

