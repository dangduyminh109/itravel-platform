package com.itravel.platform.modules.tour.application.port.in.category;

import com.itravel.platform.modules.tour.application.command.model.category.CreateCategoryCommand;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;

public interface CreateCategoryUseCase {
    CategoryDetailDTO execute(CreateCategoryCommand command);
}
