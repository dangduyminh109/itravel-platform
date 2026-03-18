package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.application.query.CustomerGeneralInfo;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity,String> {

    @Query(value = "SELECT " +
            "    COUNT(*) AS totalCustomers, " +
            "    COUNT(CASE WHEN acc.status = 1 THEN 1 END) AS totalActiveCustomers, " +
            "    COUNT(CASE WHEN acc.status = 0 THEN 1 END) AS totalInactiveCustomers, " +
            "    COUNT(" +
            "       CASE " +
            "           WHEN acc.created_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
            "           THEN 1 " +
            "       END" +
            "    ) AS newCustomers " +
            "FROM customer c " +
            "LEFT JOIN account_link al ON al.target_id = c.id " +
            "LEFT JOIN account acc ON al.account_id = acc.id; ",
            nativeQuery = true)
    CustomerGeneralInfo getCustomerGeneralInfo();

    @Query(
            value = "SELECT c.* FROM customer c " +
                    "JOIN account_link al ON c.id = al.target_id " +
                    "JOIN account acc ON al.account_id = acc.id " +

                    "WHERE (:keyword IS NULL OR :keyword = '' " +

                    "OR LOWER(c.full_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(acc.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "c.phone_number LIKE CONCAT('%', :keyword, '%'))" +

                    "AND (:isDeleted IS NULL" +
                    "    OR (:isDeleted = true AND acc.deleted_at IS NOT NULL)" +
                    "    OR (:isDeleted = false AND acc.deleted_at IS NULL)" +
                    ")",

            countQuery = "SELECT c.* FROM customer c " +
                    "JOIN account_link al ON c.id = al.target_id " +
                    "JOIN account acc ON al.account_id = acc.id " +

                    "WHERE (:keyword IS NULL OR :keyword = '' " +

                    "OR LOWER(c.full_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(acc.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "c.phone_number LIKE CONCAT('%', :keyword, '%'))" +

                    "AND (:isDeleted IS NULL" +
                    "    OR (:isDeleted = true AND acc.deleted_at IS NOT NULL)" +
                    "    OR (:isDeleted = false AND acc.deleted_at IS NULL)" +
                    ")",
            nativeQuery = true
    )
    Page<CustomerJpaEntity> searchCustomer(@Param("keyword") String keyword, Pageable pageable,@Param("isDeleted")  boolean isDeleted);

}
