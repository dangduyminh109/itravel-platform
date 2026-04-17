package com.itravel.platform.modules.location.application.command.location;

import com.itravel.platform.modules.location.domain.location.LocationStatus;
import com.itravel.platform.modules.location.domain.location.LocationId;

public record UpdateStatusLocationCommand(
        LocationId id,
        LocationStatus status
) {
}
