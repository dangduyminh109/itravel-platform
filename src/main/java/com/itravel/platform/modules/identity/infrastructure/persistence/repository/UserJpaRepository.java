package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity,String> {

    @Query(
            value = "select u from UserJpaEntity u " +
                    "where :keyword is null or lower(u.fullName) " + "LIKE lower(concat('%', :keyword, '%'))",

            countQuery = "select COUNT(u) FROM UserJpaEntity u " +
                    "where :keyword is null or lower(u.fullName) LIKE lower(concat('%', :keyword, '%'))"
    )
    Page<UserJpaEntity> getUsers(String keyword, Pageable pageable);
}