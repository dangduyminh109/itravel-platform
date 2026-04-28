package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TourJpaRepository extends JpaRepository<TourJpaEntity, String> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);

    Optional<TourJpaEntity> findBySlug(String slug);

    @Query(
            value = "SELECT t.* FROM tour t " +
                    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                    "AND (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND t.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND t.deleted_at IS NULL)" +
                    ") " +
                    "AND (:status IS NULL OR :status = t.status) " +
                    "AND (:categoryId IS NULL OR t.category_id = :categoryId) " +
                    "AND (:departureId IS NULL OR t.departure_location_id = :departureId)",
            countQuery = "SELECT COUNT(*) FROM tour t " +
                    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                    "AND (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND t.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND t.deleted_at IS NULL)" +
                    ") " +
                    "AND (:status IS NULL OR :status = t.status) " +
                    "AND (:categoryId IS NULL OR t.category_id = :categoryId) " +
                    "AND (:departureId IS NULL OR t.departure_location_id = :departureId)",
            nativeQuery = true
    )
    Page<TourJpaEntity> getTours(
            @Param("keyword") String keyword,
            @Param("isDeleted") Boolean isDeleted,
            @Param("status") String status,
            @Param("categoryId") Long categoryId,
            @Param("departureId") Long departureId,
            Pageable pageable
    );
}
