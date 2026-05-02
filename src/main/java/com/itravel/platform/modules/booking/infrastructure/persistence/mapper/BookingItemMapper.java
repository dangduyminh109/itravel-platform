package com.itravel.platform.modules.booking.infrastructure.persistence.mapper;

import com.itravel.platform.modules.booking.domain.bookingItem.BookingItem;
import com.itravel.platform.modules.booking.domain.bookingItem.BookingItemId;
import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingItemJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingItemMapper {

    static BookingItemJpaEntity toBookingItemJpaEntity(BookingItem domain) {
        if (domain == null)
            return null;

        return BookingItemJpaEntity.builder()
                .id(domain.getId() != null ? domain.getId().value() : null)
                .serviceType(domain.getServiceType())
                .referenceId(domain.getReferenceId())
                .snapshotData(domain.getSnapshotData())
                .priceBreakdown(domain.getPriceBreakdown())
                .build();
    }

    static BookingItem toBookingItemDomain(BookingItemJpaEntity entity) {
        if (entity == null)
            return null;
        
        return BookingItem.fromExisting(
                entity.getId() != null ? new BookingItemId(entity.getId()) : null,
                entity.getServiceType(),
                entity.getReferenceId(),
                entity.getSnapshotData(),
                entity.getPriceBreakdown());
    }
}
