package com.itravel.platform.modules.booking.application.command.service.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.CreateBookingCommand;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.exception.InvalidPassengerAgeException;
import com.itravel.platform.modules.booking.application.exception.InventoryUnavailableException;
import com.itravel.platform.modules.booking.application.port.in.booking.CreateBookingUseCase;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.application.port.out.booking.event.BookingEventPublisher;
import com.itravel.platform.modules.booking.application.port.out.booking.external.TourCatalogPort;
import com.itravel.platform.modules.booking.domain.booking.Booking;
import com.itravel.platform.modules.booking.domain.bookingItem.BookingItem;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceSnapshot;
import com.itravel.platform.modules.booking.domain.bookingItem.PriceLine;
import com.itravel.platform.modules.booking.domain.passenger.Passenger;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateBookingService implements CreateBookingUseCase {
    BookingRepository bookingRepository;
    TourCatalogPort tourCatalogPort;
    BookingEventPublisher eventPublisher;

    @Override
    @Transactional
    public BookingDetailDTO execute(CreateBookingCommand command) {

        if (command.serviceType().equals(ServiceType.TOUR)) {
            int requiredQuantity = command.passengers().size();
            boolean isAvailable = tourCatalogPort.verifyAndLockInventory(command.referenceId(), requiredQuantity);
            if (!isAvailable) {
                throw new InventoryUnavailableException();
            }
        }

        List<Passenger> passengers = new ArrayList<>();
        for (CreateBookingCommand.PassengerCommand pc : command.passengers()) {
            Passenger passenger = Passenger.classify(
                    pc.fullName(),
                    pc.dateOfBirth(),
                    pc.gender(),
                    pc.identityNumber(),
                    LocalDate.now()
            );
            if (!passenger.matchesType(pc.selectedTicketType())) {
                throw new InvalidPassengerAgeException();
            }
            passengers.add(passenger);
        }

        ServiceSnapshot snapshot = null;
        List<PriceLine> priceLines = List.of();

        if (command.serviceType().equals(ServiceType.TOUR)) {
            snapshot = tourCatalogPort.getTourSnapshot(command.referenceId());
            priceLines = tourCatalogPort.getPriceBreakdown(
                    command.referenceId(),
                    passengers.stream().map(Passenger::getPassengerType).toList()
            );
        }

        BookingItem bookingItem = BookingItem.create(
                command.serviceType(),
                command.referenceId(),
                snapshot,
                priceLines
        );

        Booking booking = Booking.create(
                command.contactInfo(),
                command.serviceType(),
                command.customerId(),
                command.note(),
                List.of(bookingItem),
                passengers
        );

        BookingDetailDTO bookingDetailDTO = bookingRepository.save(booking);

        booking.getDomainEvents().forEach(eventPublisher::publish);
        booking.clearDomainEvents();

        return bookingDetailDTO;
    }
}
