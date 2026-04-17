package com.itravel.platform.modules.tour.application.port.in.category;

import com.itravel.platform.modules.tour.application.command.model.category.DeleteCategoryCommand;

public interface DestroyCategoryUseCase {
    void execute(DeleteCategoryCommand command);
}
