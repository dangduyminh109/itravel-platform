package com.itravel.platform.modules.tour.application.port.in.category;

import com.itravel.platform.modules.tour.application.command.model.category.UpdateStatusCategoryCommand;

public interface UpdateStatusCategoryUseCase {
    void execute(UpdateStatusCategoryCommand command);
}
