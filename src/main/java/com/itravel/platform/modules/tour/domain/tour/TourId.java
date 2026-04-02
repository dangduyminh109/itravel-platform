package com.itravel.platform.modules.tour.domain.tour;

import java.util.UUID;

public record TourId(String value) {
    public static TourId generate(){
        return new TourId(UUID.randomUUID().toString());
    }
}
