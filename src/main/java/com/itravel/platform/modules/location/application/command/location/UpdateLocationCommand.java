package com.itravel.platform.modules.location.application.command.location;

import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationType;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationName;

public record UpdateLocationCommand(
        LocationId id,
        LocationName name,
        LocationStatus status,
        LocationType type,
        LocationId parentId
) {
}
