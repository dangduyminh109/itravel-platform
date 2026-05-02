package com.itravel.platform.modules.booking.infrastructure.persistence.repository;

import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingItemJpaRepository extends JpaRepository<BookingItemJpaEntity, String> {
}
