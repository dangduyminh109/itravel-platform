package com.itravel.platform.modules.tour.application.command.model.category;

import com.itravel.platform.modules.tour.domain.category.CategoryStatus;
import com.itravel.platform.modules.tour.domain.category.CategoryName;

public record CreateCategoryCommand(
        CategoryName name,
        String description,
        CategoryStatus status
) {}
