package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity,Long> {
    Optional<RoleJpaEntity> findByName(String name);
    Boolean existsByName(String name);

    @Query("""
        SELECT r 
        FROM RoleJpaEntity r 
        WHERE r.id IN :ids 
        ORDER BY r.createdAt DESC
    """)
    List<RoleJpaEntity> findByIdIn(@Param("ids") Set<Long> ids);

    @Query("""
        SELECT r 
        FROM RoleJpaEntity r 
        WHERE (:keyword IS NULL OR r.name LIKE CONCAT('%', :keyword, '%')) AND (r.status = :status OR :status IS NULL)
        ORDER BY r.createdAt DESC
    """)
    List<RoleJpaEntity> findByStatus(
            @Param("status") String status,
            @Param("keyword") String keyword
    );
}