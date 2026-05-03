package com.itravel.platform.modules.booking.infrastructure.adapter;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.domain.booking.Booking;
import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingJpaEntity;
import com.itravel.platform.modules.booking.infrastructure.persistence.mapper.BookingMapper;
import com.itravel.platform.modules.booking.infrastructure.persistence.repository.BookingJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingRepositoryImpl implements BookingRepository {

    BookingJpaRepository bookingJpaRepository;

    @Override
    public BookingDetailDTO save(Booking booking) {
        BookingJpaEntity entity = BookingMapper.toBookingJpaEntity(booking);
        BookingJpaEntity savedEntity = bookingJpaRepository.save(entity);
        
        return BookingMapper.toBookingDetailDTO(savedEntity);
    }

    @Override
    public Optional<Booking> findById(BookingId id) {
        return bookingJpaRepository.findById(id.value())
                .map(BookingMapper::toBookingDomain);
    }

    @Override
    public Optional<Booking> findByCode(BookingCode code) {
        return bookingJpaRepository.findByBookingCode(code.value())
                .map(BookingMapper::toBookingDomain);
    }
}
