package com.itravel.platform.modules.tour.application.port.in.category;

import com.itravel.platform.modules.tour.application.command.model.category.UpdateCategoryCommand;
import com.itravel.platform.modules.tour.application.dto.CategoryDetailDTO;

public interface UpdateCategoryUseCase {
    CategoryDetailDTO execute(UpdateCategoryCommand command);
}
