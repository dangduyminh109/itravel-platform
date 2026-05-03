package com.itravel.platform.modules.booking.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.booking.api.dto.request.CreateBookingRequest;
import com.itravel.platform.modules.booking.api.dto.response.BookingListItemResponse;
import com.itravel.platform.modules.booking.api.dto.response.BookingResponse;
import com.itravel.platform.modules.booking.api.mapper.BookingRestMapper;
import com.itravel.platform.modules.booking.application.command.model.booking.CancelBookingCommand;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.port.in.facade.BookingCommandFacade;
import com.itravel.platform.modules.booking.application.port.in.facade.BookingQueryFacade;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingCommandFacade bookingCommandFacade;
    BookingQueryFacade bookingQueryFacade;
    BookingRestMapper bookingRestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        BookingDetailDTO booking = bookingCommandFacade.createBooking(
                bookingRestMapper.toCreateBookingCommand(request)
        );
        return ApiResponse.<BookingResponse>builder()
                .success(true)
                .message("Booking created successfully")
                .response(bookingRestMapper.toBookingResponse(booking))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> getBookingDetail(@PathVariable String id) {
        BookingDetailDTO booking = bookingQueryFacade.getBookingDetail(id);
        return ApiResponse.<BookingResponse>builder()
                .success(true)
                .response(bookingRestMapper.toBookingResponse(booking))
                .build();
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse<PageResponse<BookingListItemResponse>> getCustomerBookings(
            @PathVariable String customerId,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        Page<BookingListItemDTO> bookingPage = bookingQueryFacade.getCustomerBookings(customerId, pageable);
        PageResponse<BookingListItemResponse> response = PageResponse.<BookingListItemResponse>builder()
                .currentPage(bookingPage.getNumber())
                .pageSize(bookingPage.getSize())
                .totalElements(bookingPage.getTotalElements())
                .totalPages(bookingPage.getTotalPages())
                .data(bookingPage.getContent().stream().map(bookingRestMapper::toBookingListItemResponse).toList())
                .build();

        return ApiResponse.<PageResponse<BookingListItemResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @GetMapping
    public ApiResponse<PageResponse<BookingListItemResponse>> getAllBookings(
            @RequestParam(required = false) BookingStatus status,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        Page<BookingListItemDTO> bookingPage = bookingQueryFacade.getAllBookings(status, pageable);
        PageResponse<BookingListItemResponse> response = PageResponse.<BookingListItemResponse>builder()
                .currentPage(bookingPage.getNumber())
                .pageSize(bookingPage.getSize())
                .totalElements(bookingPage.getTotalElements())
                .totalPages(bookingPage.getTotalPages())
                .data(bookingPage.getContent().stream().map(bookingRestMapper::toBookingListItemResponse).toList())
                .build();

        return ApiResponse.<PageResponse<BookingListItemResponse>>builder()
                .success(true)
                .response(response)
                .build();
    }

    @PostMapping("/{id}/cancel")
    public ApiResponse<Void> cancelBooking(@PathVariable String id) {
        bookingCommandFacade.cancelBooking(new CancelBookingCommand(new BookingId(id)));
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Booking cancelled successfully")
                .build();
    }
}
