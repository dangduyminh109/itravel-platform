package com.itravel.platform.modules.booking.domain.bookingItem;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TourSnapshot.class, name = "TOUR")
})
public sealed interface ServiceSnapshot permits TourSnapshot {
}
