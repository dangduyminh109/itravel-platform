package com.itravel.platform.modules.location.application.command.location;

import com.itravel.platform.modules.location.domain.location.LocationStatus;
import com.itravel.platform.modules.location.domain.location.LocationType;
import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.location.domain.location.LocationName;

public record UpdateLocationCommand(
        LocationId id,
        LocationName name,
        LocationStatus status,
        LocationType type,
        LocationId parentId
) {
}
