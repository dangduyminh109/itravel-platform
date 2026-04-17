package com.itravel.platform.modules.location.share;

import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.location.domain.location.LocationName;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LocationValueObjectMapper {
    default LocationId toLocationId(Long id) {
        return id != null ? new LocationId(id) : null;
    }
    default Long fromLocationId(LocationId id) {
        return id != null ? id.value() : null;
    }

    default LocationName toLocationName(String name) {
        return name != null ? new LocationName(name) : null;
    }
    default String fromLocationName(LocationName name) {
        return name != null ? name.value() : null;
    }
}