package com.itravel.platform.modules.tour.infrastructure.persistence.repository;

import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {

    @Query(
            value = "SELECT c.* FROM category c " +
                    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                    "AND (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND c.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND c.deleted_at IS NULL)" +
                    ") AND (:status IS NULL " +
                    "    OR :status = c.status " +
                    ")",
            countQuery = "SELECT c.* FROM category c " +
                    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
                    "AND (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND c.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND c.deleted_at IS NULL)" +
                    ") AND (:status IS NULL " +
                    "    OR :status = c.status " +
                    ")",
            nativeQuery = true
    )
    Page<CategoryJpaEntity> getCategories(
            @Param("keyword") String keyword,
            Pageable pageable,
            @Param("isDeleted") Boolean isDeleted,
            @Param("status") CategoryStatus status
    );

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}

