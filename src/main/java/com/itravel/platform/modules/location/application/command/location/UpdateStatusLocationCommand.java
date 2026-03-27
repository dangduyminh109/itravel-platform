package com.itravel.platform.modules.location.application.command.location;

import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;

public record UpdateStatusLocationCommand(
        LocationId id,
        LocationStatus status
) {
}
