package com.itravel.platform.modules.location.application.command.location;

import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;

public record DeleteLocationCommand(LocationId id) {
}
