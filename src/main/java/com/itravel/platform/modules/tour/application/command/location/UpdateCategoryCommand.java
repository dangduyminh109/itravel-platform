package com.itravel.platform.modules.tour.application.command.location;

import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryName;

public record UpdateCategoryCommand(
        CategoryId id,
        CategoryName name,
        String description,
        CategoryStatus status
) {
}
