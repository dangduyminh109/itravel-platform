package com.itravel.platform.modules.booking.infrastructure.persistence.repository;

import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingJpaEntity, String> {
    Optional<BookingJpaEntity> findByBookingCode(String bookingCode);
    Page<BookingJpaEntity> findByCustomerId(String customerId, Pageable pageable);

    @Query(
            value = "SELECT b.* FROM bookings b " +
                    "WHERE b.deleted_at IS NULL " +
                    "AND (:status IS NULL OR b.status = :status)",
            countQuery = "SELECT COUNT(*) FROM bookings b " +
                    "WHERE b.deleted_at IS NULL " +
                    "AND (:status IS NULL OR b.status = :status)",
            nativeQuery = true
    )
    Page<BookingJpaEntity> findAllWithStatus(@Param("status") String status, Pageable pageable);
}
