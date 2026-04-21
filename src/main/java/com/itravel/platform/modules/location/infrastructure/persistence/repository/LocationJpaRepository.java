package com.itravel.platform.modules.location.infrastructure.persistence.repository;

import com.itravel.platform.modules.location.application.dto.LocationGeneralInfoDTO;
import com.itravel.platform.modules.location.domain.location.LocationStatus;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationJpaRepository extends JpaRepository<LocationJpaEntity, Long> {

    @Query(value = "SELECT " +
            "    COUNT(*) AS totalLocations, " +
            "    COUNT(CASE WHEN u.status = 1 THEN 1 END) AS totalActiveLocations, " +
            "    COUNT(CASE WHEN u.status = 0 THEN 1 END) AS totalInactiveLocations, " +
            "    COUNT(" +
            "       CASE " +
            "           WHEN u.created_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
            "           THEN 1 " +
            "       END" +
            "    ) AS newLocations " +
            "FROM location u ",
            nativeQuery = true)
    LocationGeneralInfoDTO getLocationGeneralInfoDTO();

    @Query(value = "SELECT u.* FROM location u " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:isDeleted IS NULL " +
            "    OR (:isDeleted = true AND u.deleted_at IS NOT NULL) " +
            "    OR (:isDeleted = false AND u.deleted_at IS NULL)" +
            ") AND (:status IS NULL " +
            "    OR :status = u.status " +
            ")",

            countQuery = "SELECT u.* FROM location u " +
                    "WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
                    +
                    "AND (:isDeleted IS NULL " +
                    "    OR (:isDeleted = true AND u.deleted_at IS NOT NULL) " +
                    "    OR (:isDeleted = false AND u.deleted_at IS NULL)" +
                    ") AND (:status IS NULL " +
                    "    OR :status = u.status " +
                    ")", nativeQuery = true)
    Page<LocationJpaEntity> getLocations(
            Pageable pageable,
            @Param("keyword") String keyword,
            @Param("isDeleted") Boolean isDeleted,
            @Param("status") LocationStatus status
    );

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    @Query(value = "SELECT u.* FROM location u " +
            "WHERE (:isDeleted IS NULL " +
            "    OR (:isDeleted = true AND u.deleted_at IS NOT NULL) " +
            "    OR (:isDeleted = false AND u.deleted_at IS NULL)" +
            ") AND (:status IS NULL " +
            "    OR :status = u.status " +
            ")", nativeQuery = true)
    List<LocationJpaEntity> getTree(
            @Param("isDeleted") Boolean isDeleted,
            @Param("status") LocationStatus status
    );
}