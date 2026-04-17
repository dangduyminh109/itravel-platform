package com.itravel.platform.modules.tour.application.command.service.tour;

import com.itravel.platform.modules.identity.application.port.out.MediaUploadPort;
import com.itravel.platform.modules.tour.application.command.model.tour.UpdateTourCommand;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.exception.CategoryNotFoundException;
import com.itravel.platform.modules.tour.application.exception.TourNameExistedException;
import com.itravel.platform.modules.tour.application.exception.TourNotFoundException;
import com.itravel.platform.modules.tour.application.port.in.tour.UpdateTourUseCase;
import com.itravel.platform.modules.tour.application.port.out.category.CategoryQueryPort;
import com.itravel.platform.modules.tour.application.port.out.tour.TourQueryPort;
import com.itravel.platform.modules.tour.application.port.out.tour.TourRepository;
import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.itinerary.exception.ItineraryNotFoundException;
import com.itravel.platform.modules.tour.domain.itinerary.exception.ItineraryTitleTooLongException;
import com.itravel.platform.modules.tour.domain.tour.Tour;
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
public class UpdateTourService implements UpdateTourUseCase {
    TourRepository repository;
    TourQueryPort queryPort;
    MediaUploadPort mediaUploadPort;
    CategoryQueryPort categoryQueryPort;

    @Transactional
    public TourDetailDTO execute(UpdateTourCommand command) {
        Tour tour = repository.findById(command.id())
                .orElseThrow(TourNotFoundException::new);

        if (command.name() != null) {
            if (queryPort.existsByNameAndIdNot(command.name().value(), command.id().value())) {
                throw new TourNameExistedException();
            }
            tour.updateName(command.name());
        }
        if (command.summary() != null) {
            tour.updateSummary(command.summary());
        }
        if (command.description() != null) {
            tour.updateDescription(command.description());
        }
        if (command.status() != null) {
            tour.updateStatus(command.status());
        }
        if (command.pricing() != null) {
            tour.updatePricing(command.pricing());
        }
        if (command.duration() != null) {
            tour.updateDuration(command.duration());
        }
        if (command.participantLimit() != null) {
            tour.updateParticipantLimit(command.participantLimit());
        }
        if (command.services() != null) {
            tour.updateServices(command.services());
        }
        if (command.categoryId() != null) {
            if(!categoryQueryPort.existsById(command.categoryId().value())){
                throw new CategoryNotFoundException();
            }
            tour.updateCategory(command.categoryId());
        }
        if (command.departureLocationId() != null) {
            tour.updateDepartureLocation(command.departureLocationId());
        }
        if (command.destinationLocationId() != null) {
            tour.updateDestinationLocation(command.destinationLocationId());
        }

        List<Itinerary> newItineraries = command.itineraries().stream()
                .map(item -> {
                    if (item.id() != null && item.id().value() != null) {
                        Itinerary existingItinerary = tour.getItineraries().stream()
                                .filter(i -> i.getId().equals(item.id()))
                                .findFirst()
                                .orElseThrow(ItineraryNotFoundException::new);
                        existingItinerary.updateDayNumber(item.dayNumber());
                        existingItinerary.updateTitle(item.title());
                        existingItinerary.updateDescription(item.description());
                        existingItinerary.updateActivities(item.activities());
                        return existingItinerary;
                    } else {
                        return Itinerary.create(item.dayNumber(), item.title(), item.description(), item.activities());
                    }
                })
                .toList();

        tour.updateItineraries(newItineraries);

        if (command.removedImageUrls() != null && !command.removedImageUrls().isEmpty()) {
            command.removedImageUrls().forEach(tour::removeTourImageByUrl);
        }

        if (command.tourImages() != null && !command.tourImages().isEmpty()) {
            List<TourImage> images = command.tourImages().stream()
                    .map(item -> {
                        String imageUrl = mediaUploadPort.uploadAvatar(item.image());
                        return TourImage.create(new ImageUrl(imageUrl), item.isThumbnail());
                    })
                    .toList();
            images.forEach(tour::addTourImage);
        }

        return repository.save(tour);
    }
}

