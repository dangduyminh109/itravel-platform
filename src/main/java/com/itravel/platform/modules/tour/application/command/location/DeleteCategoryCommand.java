package com.itravel.platform.modules.tour.application.command.location;

import com.itravel.platform.modules.tour.domain.aggregate.valueobject.CategoryId;

public record DeleteCategoryCommand(CategoryId id) {
}
