package com.itravel.platform.modules.booking.infrastructure.adapter;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingQueryPort;
import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import com.itravel.platform.modules.booking.infrastructure.persistence.mapper.BookingMapper;
import com.itravel.platform.modules.booking.infrastructure.persistence.repository.BookingJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingQueryAdapter implements BookingQueryPort {
    BookingJpaRepository repository;

    @Override
    public Optional<BookingDetailDTO> getBookingDetail(String bookingId) {
        return repository.findById(bookingId)
                .map(BookingMapper::toBookingDetailDTO);
    }

    @Override
    public Page<BookingListItemDTO> getBookingsByCustomerId(String customerId, Pageable pageable) {
        return repository.findByCustomerId(customerId, pageable)
                .map(BookingMapper::toBookingListItemDTO);
    }

    @Override
    public Page<BookingListItemDTO> getAllBookings(BookingStatus status, Pageable pageable) {
        String statusStr = status != null ? status.name() : null;
        return repository.findAllWithStatus(statusStr, pageable)
                .map(BookingMapper::toBookingListItemDTO);
    }
}
