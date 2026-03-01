package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity,String> {
    @Query(
            value = "select c from CustomerJpaEntity c " +
            "where :keyword is null or lower(c.fullName) " + "LIKE lower(concat('%', :keyword, '%'))",

            countQuery = "select COUNT(c) FROM CustomerJpaEntity c " +
                    "where :keyword is null or lower(c.fullName) LIKE lower(concat('%', :keyword, '%'))"
    )
    Page<CustomerJpaEntity> searchCustomer(@Param("keyword") String keyword, Pageable pageable);

}
