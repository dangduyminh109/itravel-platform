package com.itravel.platform.modules.booking.infrastructure.adapter;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingQueryPort;
import com.itravel.platform.modules.booking.infrastructure.persistence.mapper.BookingMapper;
import com.itravel.platform.modules.booking.infrastructure.persistence.repository.BookingJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<BookingListItemDTO> getBookingsByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId).stream()
                .map(BookingMapper::toBookingListItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingListItemDTO> getAllBookings() {
        return repository.findAll().stream()
                .map(BookingMapper::toBookingListItemDTO)
                .collect(Collectors.toList());
    }
}
