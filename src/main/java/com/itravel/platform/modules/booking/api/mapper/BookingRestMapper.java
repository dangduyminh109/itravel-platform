package com.itravel.platform.modules.booking.api.mapper;

import com.itravel.platform.modules.booking.api.dto.request.CreateBookingRequest;
import com.itravel.platform.modules.booking.api.dto.response.BookingListItemResponse;
import com.itravel.platform.modules.booking.api.dto.response.BookingResponse;
import com.itravel.platform.modules.booking.application.command.model.booking.CreateBookingCommand;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.share.BookingValueObjectMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookingValueObjectMapper.class})
public interface BookingRestMapper {

    CreateBookingCommand toCreateBookingCommand(CreateBookingRequest request);

    CreateBookingCommand.PassengerCommand toPassengerCommand(CreateBookingRequest.PassengerRequest request);

    BookingResponse toBookingResponse(BookingDetailDTO dto);

    BookingResponse.BookingItemResponse toBookingItemResponse(BookingDetailDTO.BookingItemDTO dto);

    BookingResponse.PriceLineResponse toPriceLineResponse(BookingDetailDTO.PriceLineDTO dto);

    BookingResponse.PassengerResponse toPassengerResponse(BookingDetailDTO.PassengerDetailDTO dto);

    BookingListItemResponse toBookingListItemResponse(BookingListItemDTO dto);
}
