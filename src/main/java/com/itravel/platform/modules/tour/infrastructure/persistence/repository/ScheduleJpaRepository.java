package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ScheduleJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleJpaRepository extends JpaRepository<ScheduleJpaEntity, Long> {
    @Query(
            value = "SELECT s.*  FROM schedule s " +
                    "WHERE (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND s.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND s.deleted_at IS NULL)" +
                    ") " +
                    "AND (:tourId IS NULL OR s.tour_id = :tourId) ",
            countQuery = "SELECT COUNT(*) FROM schedule s " +
                    "WHERE (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND s.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND s.deleted_at IS NULL)" +
                    ") " +
                    "AND (:tourId IS NULL OR s.tour_id = :tourId) ",
            nativeQuery = true
    )
    Page<ScheduleJpaEntity> getScheduleListFromTour(
            @Param("isDeleted") Boolean isDeleted,
            @Param("tourId") String tourId,
            Pageable pageable
    );
}

