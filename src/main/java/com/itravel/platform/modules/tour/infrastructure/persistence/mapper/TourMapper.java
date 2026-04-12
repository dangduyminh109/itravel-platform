package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.location.infrastructure.persistence.mapper.LocationMapper;
import com.itravel.platform.modules.tour.application.dto.ItineraryDTO;
import com.itravel.platform.modules.tour.application.dto.TourDetailDTO;
import com.itravel.platform.modules.tour.application.dto.TourImageDTO;
import com.itravel.platform.modules.tour.application.dto.TourListItemDTO;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.tour.*;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.CategoryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ItineraryJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourImageJpaEntity;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TourJpaEntity;
import com.itravel.platform.modules.tour.share.CommonValueObjectMapper;
import com.itravel.platform.modules.tour.share.TourValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {
                TourValueObjectMapper.class,
                CommonValueObjectMapper.class,
                CategoryMapper.class,
                LocationMapper.class,
                TourImageMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TourMapper {
    static TourJpaEntity toTourJpaEntity(
            TourJpaEntity entity,
            Tour tour,
            CategoryJpaEntity category,
            LocationJpaEntity departureLocation,
            LocationJpaEntity destinationLocation,
            List<TourImageJpaEntity> images,
            List<ItineraryJpaEntity> itineraries
    ) {;
        entity.setId(tour.getId() != null ? tour.getId().value() : null);
        entity.setName(tour.getName() != null ? tour.getName().value() : null);
        entity.setSlug(tour.getSlug() != null ? tour.getSlug().value() : null);
        entity.setSummary(tour.getSummary());
        entity.setDescription(tour.getDescription());
        entity.setStatus(tour.getStatus());
        if (tour.getPricing() != null) {
            entity.setOriginalPrice(tour.getPricing().originalPrice());
            entity.setDiscountPrice(tour.getPricing().discountPrice());
            entity.setCurrency(tour.getPricing().currency());
        }
        if (tour.getServices() != null) {
            entity.setIncludedServices(tour.getServices().includes());
            entity.setExcludedServices(tour.getServices().excludes());
        }
        if (category != null) {
            entity.setCategory(category);
        }

        if (departureLocation != null) {
            entity.setDepartureLocation(departureLocation);
        }
        if (destinationLocation != null) {
            entity.setDestinationLocation(destinationLocation);
        }

        if (tour.getDuration() != null) {
            entity.setDurationDays(tour.getDuration().days());
            entity.setDurationNights(tour.getDuration().nights());
        }

        if (tour.getParticipantLimit() != null) {
            entity.setMinParticipants(tour.getParticipantLimit().minParticipants());
            entity.setMaxParticipants(tour.getParticipantLimit().maxParticipants());
        }

        // Handle tour images
        List<String> oldImageUrls = entity.getTourImages().stream()
                .map(TourImageJpaEntity::getImageUrl)
                .toList();
        List<String> newImageUrls = tour.getTourImages().stream()
                .map(item -> item.getImageUrl().value())
                .toList();

        entity.getTourImages().removeIf(prevImg ->
                !newImageUrls.contains(prevImg.getImageUrl())
        );

        images.forEach(item -> {
            if(!oldImageUrls.contains(item.getImageUrl())) {
                item.setTour(entity);
                entity.getTourImages().add(item);
            }
        });

        entity.getItineraries().clear();
        if (itineraries != null) {
            entity.getItineraries().addAll(itineraries);
            itineraries.forEach(item -> item.setTour(entity));
        }

        entity.setCreatedAt(tour.getCreatedAt());
        entity.setUpdatedAt(tour.getUpdatedAt());
        entity.setDeletedAt(tour.getDeletedAt());
        return entity;
    }

    static Tour toTourDomain(TourJpaEntity entity) {
        return Tour.fromExistingBuilder()
                .id(new TourId(entity.getId()))
                .status(entity.getStatus())
                .name(new TourName(entity.getName()))
                .summary(entity.getSummary())
                .slug(new Slug(entity.getSlug()))
                .description(entity.getDescription())
                .pricing(new Pricing(
                        entity.getOriginalPrice(),
                        entity.getDiscountPrice(),
                        entity.getCurrency())
                )
                .services(new Services(entity.getIncludedServices(),entity.getExcludedServices()))
                .destinationLocationId(entity.getDestinationLocation() != null
                        ? new LocationId(entity.getDestinationLocation().getId())
                        : null)
                .departureLocationId(entity.getDepartureLocation() != null
                        ? new LocationId(entity.getDepartureLocation().getId())
                        : null)
                .categoryId(entity.getCategory() != null
                        ? new CategoryId(entity.getCategory().getId())
                        : null)
                .duration(new TourDuration(entity.getDurationDays(), entity.getDurationNights()))
                .participantLimit(new ParticipantLimit(entity.getMinParticipants(), entity.getMaxParticipants()))
                .tourImages(
                        entity.getTourImages().stream().map(TourImageMapper::toTourImageDomain).collect(Collectors.toList())
                )
                .itineraries(
                        entity.getItineraries().stream().map(ItineraryMapper::toItineraryDomain).collect(Collectors.toList())
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    static TourListItemDTO toTourListItemDTO(TourJpaEntity entity) {
        String thumbnailUrl = null;
        List<TourImageJpaEntity> images = entity.getTourImages();
        if (images != null) {
            thumbnailUrl = images.stream()
                    .filter(image -> Boolean.TRUE.equals(image.getIsThumbnail()))
                    .map(TourImageJpaEntity::getImageUrl)
                    .findFirst()
                    .orElse(null);
        }

        return TourListItemDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .thumbnailUrl(thumbnailUrl)
                .originalPrice(entity.getOriginalPrice())
                .discountPrice(entity.getDiscountPrice())
                .categoryName(entity.getCategory() != null ? entity.getCategory().getName() : null)
                .build();
    }

    static TourDetailDTO toTourDetailDTO(TourJpaEntity entity) {
        List<ItineraryDTO> itineraries = entity.getItineraries().stream()
                .map(item -> ItineraryDTO.builder()
                        .id(item.getId())
                        .dayNumber(item.getDayNumber())
                        .title(item.getTitle())
                        .description(item.getDescription())
                        .activities(item.getActivities())
                        .build())
                .collect(Collectors.toList());

        List<TourImageDTO> tourImages = entity.getTourImages().stream()
                .map(item -> TourImageDTO.builder()
                        .id(item.getId())
                        .imageUrl(item.getImageUrl())
                        .isThumbnail(item.getIsThumbnail())
                        .build())
                .collect(Collectors.toList());

        return TourDetailDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .summary(entity.getSummary())
                .description(entity.getDescription())
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .originalPrice(entity.getOriginalPrice())
                .discountPrice(entity.getDiscountPrice())
                .currency(entity.getCurrency() != null ? entity.getCurrency().name() : null)
                .durationDays(entity.getDurationDays())
                .durationNights(entity.getDurationNights())
                .minParticipants(entity.getMinParticipants())
                .maxParticipants(entity.getMaxParticipants())
                .includedServices(entity.getIncludedServices())
                .excludedServices(entity.getExcludedServices())
                .categoryName(entity.getCategory() != null ? entity.getCategory().getName() : null)
                .departureLocationName(entity.getDepartureLocation() != null ? entity.getDepartureLocation().getName() : null)
                .destinationLocationName(entity.getDestinationLocation() != null ? entity.getDestinationLocation().getName() : null)
                .itineraries(itineraries)
                .tourImages(tourImages)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
