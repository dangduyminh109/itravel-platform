package com.itravel.platform.modules.tour.application.command.location;

import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;

public record UpdateStatusCategoryCommand(
        CategoryId id,
        CategoryStatus status
) {
}
