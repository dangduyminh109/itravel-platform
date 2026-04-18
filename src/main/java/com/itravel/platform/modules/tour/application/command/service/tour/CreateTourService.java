package com.itravel.platform.modules.tour.application.command.service.tour;

import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.tour.application.command.model.schedule.CreateScheduleCommand;
import com.itravel.platform.modules.tour.application.command.model.tour.CreateTourCommand;
import com.itravel.platform.modules.tour.application.command.service.schedule.CreateScheduleService;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.exception.TourNameExistedException;
import com.itravel.platform.modules.tour.application.port.in.tour.CreateTourUseCase;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import com.itravel.platform.modules.tour.application.port.out.tour.TourQueryPort;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.tour.Tour;
import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import com.itravel.platform.modules.tour.domain.tourImage.ImageUrl;
import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateTourService implements CreateTourUseCase {
    TourRepository repository;
    TourQueryPort queryPort;
    MediaUploadPort mediaUploadPort;
    CategoryQueryPort categoryQueryPort;
    CreateScheduleService createScheduleService;

    @Transactional
    public TourDetailDTO execute(CreateTourCommand command) {
        if (queryPort.existsByName(command.name().value())) {
            throw new TourNameExistedException();
        }

        if (command.categoryId() != null && !categoryQueryPort.existsById(command.categoryId().value())) {
            throw new CategoryNotFoundException();
        }

        List<Itinerary> itineraries = command.itineraries().stream()
                .map(item -> Itinerary.create(item.dayNumber(), item.title(), item.description(), item.activities()))
                .toList();

        TourStatus status = command.status() != null ? command.status() : TourStatus.DRAFT;

        List<TourImage> images = command.tourImages().stream()
                .map(item -> {
                    String imageUrl = mediaUploadPort.uploadAvatar(item.image());
                    return TourImage.create(new ImageUrl(imageUrl), item.isThumbnail());
                })
                .toList();

        Tour tour = Tour.create(
                command.name(),
                command.summary(),
                command.description(),
                status,
                command.pricing(),
                command.duration(),
                command.participantLimit(),
                command.services(),
                command.categoryId(),
                command.departureLocationId(),
                command.destinationLocationId(),
                itineraries,
                images
        );

        TourDetailDTO tourDetailDTO = repository.save(tour);

        command.schedules().forEach(item -> {
            CreateScheduleCommand scheduleCommand = new CreateScheduleCommand(
                item.departureDate(),
                item.availableSeats(),
                item.surcharge(),
                item.status(),
                tour.getId()
                );
            createScheduleService.execute(scheduleCommand);
        });

        return tourDetailDTO;
    }
}
