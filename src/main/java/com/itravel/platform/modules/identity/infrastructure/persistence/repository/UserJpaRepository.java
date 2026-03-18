package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.application.query.UserGeneralInfo;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity,String> {

    @Query(value = "SELECT " +
            "    COUNT(*) AS totalUsers, " +
            "    COUNT(CASE WHEN acc.status = 1 THEN 1 END) AS totalActiveUsers, " +
            "    COUNT(CASE WHEN acc.status = 0 THEN 1 END) AS totalInactiveUsers, " +
            "    COUNT(" +
            "       CASE " +
            "           WHEN acc.created_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
            "           THEN 1 " +
            "       END" +
            "    ) AS newUsers " +
            "FROM user u " +
            "LEFT JOIN account_link al ON al.target_id = u.id " +
            "LEFT JOIN account acc ON al.account_id = acc.id; ",
            nativeQuery = true)
    UserGeneralInfo getUserGeneralInfo();

    @Query(
            value = "SELECT u.* FROM user u " +
                    "JOIN account_link al ON u.id = al.target_id " +
                    "JOIN account acc ON al.account_id = acc.id " +

                    "WHERE (:keyword IS NULL OR :keyword = '' " +

                    "OR LOWER(u.full_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(acc.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "u.phone_number LIKE CONCAT('%', :keyword, '%'))" +

                    "AND (:isDeleted IS NULL" +
                    "    OR (:isDeleted = true AND acc.deleted_at IS NOT NULL)" +
                    "    OR (:isDeleted = false AND acc.deleted_at IS NULL)" +
                    ")",

            countQuery = "SELECT u.* FROM user u " +
                    "JOIN account_link al ON u.id = al.target_id " +
                    "JOIN account acc ON al.account_id = acc.id " +

                    "WHERE (:keyword IS NULL OR :keyword = '' " +

                    "OR LOWER(u.full_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(acc.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "u.phone_number LIKE CONCAT('%', :keyword, '%'))" +

                    "AND (:isDeleted IS NULL" +
                    "    OR (:isDeleted = true AND acc.deleted_at IS NOT NULL)" +
                    "    OR (:isDeleted = false AND acc.deleted_at IS NULL)" +
                    ")",

            nativeQuery = true
    )
    Page<UserJpaEntity> getUsers(@Param("keyword") String keyword, Pageable pageable,@Param("isDeleted") boolean isDeleted);
}