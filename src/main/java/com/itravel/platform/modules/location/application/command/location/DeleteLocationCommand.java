package com.itravel.platform.modules.location.application.command.location;

import com.itravel.platform.modules.location.domain.location.LocationId;

public record DeleteLocationCommand(LocationId id) {
}
