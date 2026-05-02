package com.itravel.platform.modules.booking.infrastructure.persistence.repository;

import com.itravel.platform.modules.booking.infrastructure.persistence.entity.PassengerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerJpaRepository extends JpaRepository<PassengerJpaEntity, String> {
}
