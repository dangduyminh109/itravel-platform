package com.itravel.platform.modules.booking.infrastructure.persistence.repository;

import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingJpaEntity, String> {
    Optional<BookingJpaEntity> findByBookingCode(String bookingCode);
    List<BookingJpaEntity> findByCustomerId(String customerId);
}
