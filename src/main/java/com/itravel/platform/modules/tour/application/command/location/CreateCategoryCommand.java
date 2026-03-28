package com.itravel.platform.modules.tour.application.command.location;

import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryName;

public record CreateCategoryCommand(
        CategoryName name,
        String description,
        CategoryStatus status
) {}
